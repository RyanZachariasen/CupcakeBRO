package app.controllers;

import app.entities.Bottoms;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class BottomsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/bottoms", ctx -> showBottoms(ctx, connectionPool));
    }

    private static void showBottoms(Context ctx, ConnectionPool connectionPool) {
        try {
            List<Bottoms> bottomsList = BottomsMapper.getAllBottoms(connectionPool);
            ctx.attribute("bottomsList", bottomsList);
            ctx.render("bottoms.html");
        } catch (DatabaseException e) {
            ctx.status(500).result("Error fetching bottoms from database: " + e.getMessage());
        }
    }
}
