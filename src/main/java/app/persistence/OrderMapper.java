package app.persistence;

import app.entities.Order;
import app.entities.OrderLine;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static app.persistence.OrderlineMapper.bottomsID;
import static app.persistence.OrderlineMapper.toppingsID;


public class OrderMapper {

    public static List<Order> getAllOrdersForCurrentUser(int userID, ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orderlist = new ArrayList<>();

        String sql = "SELECT ol.quantity, t.toppingname, t.toppingprice, b.bottomname, b.bottomprice " +
                "FROM orderline ol " +
                "JOIN toppings t ON ol.\"toppingsID\" = t.\"toppingsID\" " +
                "JOIN bottoms b ON ol.\"bottomsID\" = b.\"bottomsID\" " +
                "JOIN \"order\" o ON ol.\"orderID\" = o.\"orderID\" " +
                "WHERE o.\"userID\" = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userID); // Set the user ID parameter in the query
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Retrieve values from the result set
                int quantity = rs.getInt("quantity");
                String toppingName = rs.getString("toppingname");
                int toppingPrice = rs.getInt("toppingprice");
                String bottomName = rs.getString("bottomname");
                int bottomPrice = rs.getInt("bottomprice");

                // Calculate total price (topping price + bottom price)
                int totalPrice = toppingPrice + bottomPrice;

                // Check if an order with this ID already exists in the list
                int orderID = rs.getInt("orderID");
                Order order = orderlist.stream().filter(o -> o.getOrderID() == orderID).findFirst().orElse(null);



                // If order doesn't exist, create a new one and add to the list
                if (order == null) {
                    order = new Order(orderID, userID);
                    orderlist.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving orders for user " + userID, e.getMessage());
        }
        return orderlist;
    }


    public static int createOrderID(int userID, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO \"order\" (\"userID\") VALUES (?) RETURNING \"orderID\"";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("orderID");
            } else {
                throw new DatabaseException("ingen orderID returned");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Databasefejl", e.getMessage());
        }
    }

    public static void orderOrderline(int userID, int quantity, int toppingsID, int bottomsID, ConnectionPool connectionPool) {
        try {
            int orderID = OrderMapper.createOrderID(userID, connectionPool);

            OrderlineMapper.createOrderline(quantity, toppingsID, bottomsID, orderID, connectionPool);

        } catch (DatabaseException e) {
            // Handle exception
            e.printStackTrace(); // This will print the exception details to the console
        }
    }
}