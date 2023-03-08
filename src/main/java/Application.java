import Controller.TwoFactorController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;

public class Application {
    public static void main(String[] args) {
//        this line is just for testing that your tables get set up correctly
//        if not, you'll get a stack trace
        ConnectionSingleton.getConnection();

        TwoFactorController tfController = new TwoFactorController();
        Javalin app = tfController.startAPI();
        app.start(8080);
    }
}
