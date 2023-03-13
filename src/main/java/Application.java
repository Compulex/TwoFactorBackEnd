import Controller.TwoFactorController;
import Util.ConnectionSingleton;
import io.javalin.Javalin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
//        this line is just for testing that your tables get set up correctly
//        if not, you'll get a stack trace
        ConnectionSingleton.getConnection();

        TwoFactorController tfController = new TwoFactorController();
        Javalin app = tfController.startAPI();
        app.start(8080);

        //testRegister();
        //testGetUser();
        //System.out.println("Count: " + tfController.getCount());
    }

    private static void testRegister() throws IOException, InterruptedException {

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/register"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"user\", " +
                        "\"password\": \"password\" }"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse response = HttpClient.newHttpClient().send(postRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status 1: " + response.statusCode());

        HttpRequest pr = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/register"))
                .POST(HttpRequest.BodyPublishers.ofString("{" +
                        "\"username\": \"user\", " +
                        "\"password\": \"9@55**\" }"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse rsp = HttpClient.newHttpClient().send(pr, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status 2: " + rsp.statusCode());
    }
}

/*
    Extra code:
 */
