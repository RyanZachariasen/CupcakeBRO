package app.persistence;

import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class OrderlineMapper {
    static int quantity;
    static int toppingsID;
    static int bottomsID;
    int orderID;

    public static void createOrderline(int quantity, int toppingsID, int bottomsID, int orderID, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "insert into orderline (quantity,\"toppingsID\", \"bottomsID\",\"orderID\") values (?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, quantity);
            ps.setInt(2, toppingsID);
            ps.setInt(3, bottomsID);
            ps.setInt(4, orderID);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }
}


