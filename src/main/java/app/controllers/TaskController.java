package app.controllers;

/*
import app.entities.Bottoms;
import app.entities.Task;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.BottomsMapper;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class TaskController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.post("addtask", ctx -> addtask(ctx, connectionPool));
        app.post("done", ctx -> done(ctx, true, connectionPool));
        app.post("undo", ctx -> done(ctx, false, connectionPool));
        app.post("deletetask", ctx -> deletetask(ctx, connectionPool));
        app.post("edittask", ctx -> edittask(ctx, connectionPool));
        app.post("updatetask", ctx -> updatetask(ctx, connectionPool));
    }

    private static void updatetask(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        try {
            int taskId = Integer.parseInt(ctx.formParam("taskId"));
            String taskName = ctx.formParam("taskname");
            TaskMapper.update(taskId, taskName, connectionPool);
            List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("task.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    private static void edittask(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        try {
            int taskId = Integer.parseInt(ctx.formParam("taskId"));
            Task task = TaskMapper.getTaskById(taskId, connectionPool);
            ctx.attribute("task", task);
            ctx.render("edittask.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    private static void deletetask(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        try {
            int taskId = Integer.parseInt(ctx.formParam("taskId"));
            TaskMapper.delete(taskId, connectionPool);
            List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("task.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    private static void done(Context ctx, boolean done, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        try {
            int taskId = Integer.parseInt(ctx.formParam("taskId"));
            TaskMapper.setDoneTo(done, taskId, connectionPool);
            List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
            ctx.attribute("taskList", taskList);
            ctx.render("task.html");
        } catch (DatabaseException | NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    private static void addtask(Context ctx, ConnectionPool connectionPool) {
        String taskName = ctx.formParam("taskname");

        User user = ctx.sessionAttribute("currentUser");
        try {
            if (!taskName.isEmpty()) {
                Task newTask = TaskMapper.addTask(user, taskName, connectionPool);
                List<Task> taskList = TaskMapper.getAllTasksPerUser(user.getUserID(), connectionPool);
                ctx.attribute("taskList", taskList);
                ctx.render("task.html");
            } else {

            }
        } catch (DatabaseException e) {
            ctx.attribute("message", "Sum ting wong!");
            ctx.render("task.html");
        }
    }

}

*/