package Service;

import DAO.UserDAO;
import Model.User;

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
        /*User existingUser = userDAO.get;

        //if username is not found
        if(existingUser == null)
            return userDAO.insertUser(user);
        else
            rerturn null;*/

        return userDAO.insertUser(user);
    }
}
