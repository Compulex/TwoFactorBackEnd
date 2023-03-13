package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import Model.User;
import Service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class TwoFactorController {
    UserService userService;
    //Code service

    public TwoFactorController()
    {
        this.userService = new UserService();
        //Code Service
    }

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postUserHandler);

        app.get("/users/{user_id}/{code}", ctx ->{
            String user_id = ctx.pathParam("user_id");
            int user_code = Integer.parseInt(ctx.pathParam("code"));
            getVerifiedUser(ctx, Integer.parseInt(user_id), user_code);
        });

        //get user
        app.get("/users/{user_id}", ctx ->{
            String user_id = ctx.pathParam("user_id");
            getUserByIdHandler(ctx, Integer.parseInt(user_id));
        });
        return app;
    }

    private void postUserHandler(Context ctx)throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User user = om.readValue(ctx.body(), User.class);
        User insertedUser = userService.addUser(user);

        if (insertedUser != null)
        {
            ctx.json(om.writeValueAsString(insertedUser));
            ctx.status(200);
        }

        else{
            if(user.password.length() < 5)
                ctx.json(om.writeValueAsString("Password has to be at least 5 characters long"));
            else if(user.username.equals(""))
                ctx.json(om.writeValueAsString("No blank usernames allowed"));
            else
                ctx.json(om.writeValueAsString("User name already exists"));

            ctx.status(400);
        }
    }

    private void getVerifiedUser(Context ctx, int id, int code)throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        User insertedUser = userService.getUserById(id);

        if (code == insertedUser.code)
        {
            ctx.json(om.writeValueAsString("You have entered the correct code"));
            ctx.status(200);
        }

        else{
            ctx.json(om.writeValueAsString("You have entered the wrong code, try again"));
            ctx.status(400);
        }
    }

    private void getUserByIdHandler(Context ctx, int uid){
        User user = userService.getUserById(uid);
        if(user == null)
            ctx.status(400);
        else
            ctx.json(user);
    }//getUserByIdHandler

    public int getCount(){
        return userService.getUserCount();
    }
}
