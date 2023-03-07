package DAO;

import Util.ConnectionSingleton;

import java.sql.*;

public class UserDAO {

    public User insertUser(User user){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //sql statement
            String sql = "insert into UserData(username, password, code) values(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting prepared Statements Strings
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3,user.getCode());

            ps.executeUpdate();

            //id of user is auto generated
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int generated_user_id = (int) rs.getLong(1);
                return new User(generated_user_id, user.getUsername(), user.getPassword(), user.getCode());
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }//insertUser


}
