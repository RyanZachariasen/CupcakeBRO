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
        List<OrderLineDetails> orderLineDetailsList = new ArrayList<>();

        String sql = "SELECT ol.quantity, t.toppingname, t.toppingprice, b.bottomname, b.bottomprice\n" +
                "FROM orderline ol\n" +
                "JOIN toppings t ON ol.\"toppingsID\" = t.\"toppingsID\"\n" +
                "JOIN bottoms b ON ol.\"bottomsID\" = b.\"bottomsID\"\n" +
                "JOIN \"order\" o ON ol.\"orderID\" = o.\"orderID\"\n" +
                "WHERE o.\"userID\" = ?;\n";


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

                OrderLineDetails orderLineDetails = new OrderLineDetails(quantity, toppingName, toppingPrice, bottomName, bottomPrice);
                orderLineDetailsList.add(orderLineDetails);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database error while retrieving order line details", e.getMessage());
        }

        return orderLineDetailsList;
    }
}
