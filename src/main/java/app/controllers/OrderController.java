package app.controllers;

import app.entities.OrderLine;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import app.persistence.OrderlineMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class OrderController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/makecupcake", ctx -> makeCupcake(ctx, connectionPool));
        app.get("/makecupcake", ctx -> ctx.render("homepage.html"));
    }


    public static void makeCupcake(Context ctx, ConnectionPool connectionPool) throws DatabaseException {

        //Hent form parametre
        User currentUser = ctx.sessionAttribute("currentUser");
        int quantity = Integer.parseInt(ctx.formParam("quantity"));
        int toppingsID = Integer.parseInt(ctx.formParam("toppingsID"));
        int bottomsID = Integer.parseInt(ctx.formParam("bottomsID"));
        //int orderID = Integer.parseInt(ctx.formParam("orderID"));

        OrderMapper.orderOrderline(currentUser.getUserID(), quantity,  toppingsID,  bottomsID, connectionPool);
        ctx.attribute("message", "got ur cupcake created");
        // ctx.redirect("homepage.html");
        ToppingsController.renderHomePage(ctx,connectionPool);
    }
}

