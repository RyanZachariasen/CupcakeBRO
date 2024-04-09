package app.controllers;

import app.entities.OrderLine;
import app.entities.OrderLineDetails;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderLineDetailsMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class OrderLineDetailsController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("/orderlinedetails", ctx -> getOrderLineDetails(ctx, connectionPool));
        app.get("/orderlinedetails", ctx -> ctx.render("orderlinedetails.html"));
    }

    public static void getOrderLineDetails(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        // Retrieve the userID from the current session

        User currentUser = ctx.sessionAttribute("currentUser");
        int userID = currentUser.getUserID();

        try {
        List<OrderLineDetails>orderLine = OrderLineDetailsMapper.getOrderLineDetails(userID,connectionPool);
        ctx.attribute("orderLine", orderLine);
        } catch (DatabaseException e) {
            ctx.status(500).result("Error retrieving order line details: " + e.getMessage());
        }
    }



}
