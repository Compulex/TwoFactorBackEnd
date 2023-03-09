package Model;
import java.util.Random;

public class User {
    public int id;

    public String username;

    public String password;

    public int code;

    public User()
    {

    }

    public User(int id, String username, String password)
    {
        Random rand = new Random();
        this.code = rand.nextInt(999999);
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode()
    {
        Random rand = new Random();
        code = rand.nextInt(999999);
        this.code = code;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
