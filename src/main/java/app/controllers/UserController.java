package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("login", ctx -> login(ctx, connectionPool));
        app.get("logout", ctx -> logout(ctx));
        app.get("createuser", ctx -> ctx.render("createuser.html"));
        app.post("createuser", ctx -> createUser(ctx, connectionPool));
    }

    private static void createUser(Context ctx, ConnectionPool connectionPool) {
        //Hent form parametre
        String email = ctx.formParam("email");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        if (password1.equals(password2)) {
            try {
                UserMapper.createuser(email, password1, connectionPool);
                ctx.attribute("message", "Account -" + email + "- has been created. \n Please login.");
                ctx.render("frontpage.html");
            } catch (DatabaseException e) {
                ctx.attribute("message", "Username already exists.");
                ctx.render("createuser.html");
            }
        } else {
            ctx.attribute("message", "The passwords need to match.");
            ctx.render("createuser.html");
        }
    }

    private static void logout(Context ctx) {
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }

    public static void login(Context ctx, ConnectionPool connectionPool) {
        //Hent form parametre
        String email = ctx.formParam("email");
        String password = ctx.formParam("password");

        //Check om bruger findes i DB med de angivne username + password
        try {
            User user = UserMapper.login(email, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);

            ctx.attribute("role", user.getRole());

            ctx.attribute("wallet", user.getWallet());

            ctx.render("homepage.html");
        } catch (DatabaseException e) {
            //Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message", e.getMessage());
            ctx.render("frontpage.html");
        }
    }



}
