package app;

import app.config.ThymeleafConfig;
import app.controllers.*;
import app.entities.Order;
import app.exceptions.DatabaseException;
import app.persistence.*;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;


public class Main {
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "olskercupcakes";
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx -> ctx.render("frontpage.html"));

        UserController.addRoutes(app, connectionPool);
        //TaskController.addRoutes(app, connectionPool);
        ToppingsController.addRoutes(app, connectionPool);
        BottomsController.addRoutes(app, connectionPool);
        OrderController.addRoutes(app,connectionPool);
        OrderLineDetailsController.addRoutes(app, connectionPool);
        int userID = 3; // Example userID
        int quantity = 2; // Example quantity
        int toppingsID = 1; // Example toppingsID
        int bottomsID = 1; // Example bottomsID


    }
}