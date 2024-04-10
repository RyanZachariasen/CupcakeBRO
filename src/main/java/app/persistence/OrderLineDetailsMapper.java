package app.persistence;

import app.entities.OrderLineDetails;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDetailsMapper {

    public static List<OrderLineDetails> getOrderLineDetails(int userID, ConnectionPool connectionPool) throws DatabaseException {

        System.out.println("Nu er du i getOrderLineDetails mapper");

        System.out.println("Executing SQL query to retrieve order line details for userID: " + userID);

        List<OrderLineDetails> orderLineDetailsList = new ArrayList<>();

        String sql = "SELECT ol.quantity, t.toppingname, t.toppingprice, b.bottomname, b.bottomprice\n" +
                "FROM orderline ol\n" +
                "JOIN toppings t ON ol.\"toppingsID\" = t.\"toppingsID\"\n" +
                "JOIN bottoms b ON ol.\"bottomsID\" = b.\"bottomsID\"\n" +
                "JOIN \"order\" o ON ol.\"orderID\" = o.\"orderID\"\n" +
                "WHERE o.\"userID\" = ?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)

        ) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                String toppingName = rs.getString("toppingname");
                int toppingPrice = rs.getInt("toppingprice");
                String bottomName = rs.getString("bottomname");
                int bottomPrice = rs.getInt("bottomprice");
                int totalPrice = quantity * (bottomPrice + toppingPrice);
                OrderLineDetails orderLineDetails = new OrderLineDetails(quantity, toppingName, toppingPrice, bottomName, bottomPrice, totalPrice);
                orderLineDetailsList.add(orderLineDetails);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error while retrieving order line details", e.getMessage());
        }
        return orderLineDetailsList;
    }

    public static List<OrderLineDetails> getAllOrderLineDetails(String userEmail, ConnectionPool connectionPool) throws DatabaseException {
        System.out.println("Nu er du i getOrderLineDetails mapper");

        List<OrderLineDetails> orderLineDetailsList = new ArrayList<>();

        String sql = "SELECT ol.quantity, t.toppingname, t.toppingprice, b.bottomname, b.bottomprice\n" +
                "FROM orderline ol\n" +
                "JOIN toppings t ON ol.\"toppingsID\" = t.\"toppingsID\"\n" +
                "JOIN bottoms b ON ol.\"bottomsID\" = b.\"bottomsID\"\n" +
                "JOIN \"order\" o ON ol.\"orderID\" = o.\"orderID\"\n" +
                "JOIN users u ON o.\"userID\" = u.\"userID\"\n" +
                "WHERE u.email LIKE ?; ";


        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + userEmail + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                String toppingName = rs.getString("toppingname");
                int toppingPrice = rs.getInt("toppingprice");
                String bottomName = rs.getString("bottomname");
                int bottomPrice = rs.getInt("bottomprice");
                int totalPrice = quantity * (bottomPrice + toppingPrice);
                OrderLineDetails orderLineDetails = new OrderLineDetails(quantity, toppingName, toppingPrice, bottomName, bottomPrice, totalPrice);
                orderLineDetailsList.add(orderLineDetails);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error while retrieving order line details", e.getMessage());
        }

        return orderLineDetailsList;
    }


}
