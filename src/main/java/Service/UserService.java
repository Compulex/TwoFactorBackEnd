package Service;

import DAO.UserDAO;
import Model.User;

import java.util.Objects;

public class UserService {
    private UserDAO userDAO;

    public UserService(){
        userDAO = new UserDAO();
    }//constructor

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }//param constructor

    public User addUser(User user){
        //check for existing user
        User existingUser = userDAO.getUserByUsername(user.getUsername());

        //if username is not found
        if(existingUser == null){
            if(user.password.length() < 5 || Objects.equals(user.username, ""))
                return null;
            else
                return userDAO.insertUser(user);
        }
        else
            return null;
    }

    public User getUserById(int uid){
        return userDAO.getUserById(uid);
    }//getUserById

    public int getUserCount(){
        return userDAO.getUserCount();
    }
}
