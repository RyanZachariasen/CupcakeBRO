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
        app.get("/orderlinedetails", ctx -> getOrderLineDetails(ctx, connectionPool));
        app.post("/allorderlinedetails", ctx -> getAllOrderLineDetails(ctx, connectionPool));

        //app.post("/orderlinedetails", ctx -> ctx.render("orderlinedetails.html"));
    }

    public static void getOrderLineDetails(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        // Retrieve the userID from the current session

        User currentUser = ctx.sessionAttribute("currentUser");

        int userID = currentUser.getUserID();

        try {
            System.out.println("UserID: " + userID);
            List<OrderLineDetails> orderLine = OrderLineDetailsMapper.getOrderLineDetails(userID, connectionPool);
            System.out.println("Retrieved OrderLineDetails: " + orderLine.size()); // Log the size of the orderLine list

            ctx.attribute("orderLine", orderLine);
            ctx.render("orderlinedetails.html");
        } catch (DatabaseException e) {
            System.err.println("Error retrieving order line details: " + e.getMessage());
            ctx.status(500).result("Error retrieving order line details: " + e.getMessage());
        }
    }

    public static void getAllOrderLineDetails(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        // Retrieve the userID from the current session
        System.out.println("NU ER DU I GETORDERLINEDETAILS I CONTROLLEREN");

        String userEmail = ctx.formParam("email");
        try {
            List<OrderLineDetails> orderLine = OrderLineDetailsMapper.getAllOrderLineDetails(userEmail, connectionPool);
            ctx.attribute("orderLine", orderLine);
            ctx.render("adminpage.html");
        } catch (DatabaseException e) {
            ctx.status(500).result("Error retrieving order line details: " + e.getMessage());
        }
    }
}
