package DAO;

import Model.User;
import Util.ConnectionSingleton;

import java.sql.*;

public class UserDAO {

    public UserDAO(){

    }//constructor

    public User insertUser(User user){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //sql statement
            String sql = "insert into UserData(username, password, code) values(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //setting prepared Statements Strings
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            //code generated
            user.setCode();
            ps.setInt(3, user.getCode());

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

    public User getUserById(int uid){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //select sql statement
            String sql = "select * from UserData where id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            //prepared Statement int for id
            ps.setInt(1, uid);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("code"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }//getUserById

    public User getUserByUsername(String uname){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //sql statement get username
            String sql = "select * from UserData where username = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);

            //ps set string
            ps.setString(1, uname);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("code"));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public int getUserCount(){
        Connection connection = ConnectionSingleton.getConnection();
        try{
            //select sql statement
            String sql = "select COUNT(*) from UserData;";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }//getUserCount
}
