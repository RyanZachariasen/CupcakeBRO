package app.persistence;

import app.entities.Bottoms;
import app.entities.Order;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class OrderMapper {

    public static List<Order> getAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orderlist = new ArrayList<>();

        String sql = "SELECT * FROM public.\"order\" ORDER BY \"orderID\"";


        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int userID = rs.getInt("userID");

                Order orders = new Order(orderID, userID);
                orderlist.add(orders);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return orderlist;
    }


    public static List<Order> getOrders(int userID, ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orderList = new ArrayList<>();

        String sql = "SELECT * FROM public.\"order\" WHERE \"userID\" = ?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int orderID = rs.getInt("orderID");
                int userIDFromDB = rs.getInt("userID");
                Order order = new Order(orderID, userIDFromDB);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Database fejl med at hente orders", e.getMessage());
        }

        return orderList;
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
    public static void orderOrderline(int userID, int quantity, int toppingsID, int bottomsID, ConnectionPool connectionPool){
        try {
            int orderID = OrderMapper.createOrderID(userID, connectionPool);

            OrderlineMapper.createOrderline(quantity, toppingsID, bottomsID, orderID, connectionPool);

        } catch (DatabaseException e) {
            // Handle exception
            e.printStackTrace(); // This will print the exception details to the console
        }
    }

}

