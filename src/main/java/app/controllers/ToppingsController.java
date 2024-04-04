package app.controllers;

import app.entities.Bottoms;
import app.entities.Toppings;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import app.persistence.ToppingsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class ToppingsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/toppings", ctx -> showtoppings(ctx, connectionPool));
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

    public static void renderHomePage(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Toppings> toppingsList = ToppingsMapper.getAllToppings(connectionPool);
            List<Bottoms> bottomsList = BottomsMapper.getAllBottoms(connectionPool);
            ctx.attribute("toppingsList", toppingsList);
            ctx.attribute("bottomsList", bottomsList);
            ctx.render("homepage.html");
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("error.html");
        }
    }
}

