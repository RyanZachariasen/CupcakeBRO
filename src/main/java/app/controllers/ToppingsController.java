package app.controllers;

import app.entities.Toppings;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.ToppingsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ToppingsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/login", ctx -> showtoppings(ctx, connectionPool));
    }

    private static void showtoppings(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Toppings> toppingsList = ToppingsMapper.getAllToppings(connectionPool);
            ctx.attribute("toppingsList", toppingsList);
            ctx.render("homepage.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("error.html");
        }
    }
}
