package Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import Model.User;
import Model.Code;
import Service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

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

        if (user.username != null)
        {
            ctx.json(om.writeValueAsString(insertedUser));
            ctx.status(200);
        }

        else{
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
