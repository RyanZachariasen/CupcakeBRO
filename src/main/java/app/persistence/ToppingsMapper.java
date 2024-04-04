package app.persistence;

import app.entities.Toppings;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ToppingsMapper {
    public static List<Toppings> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {
        List<Toppings> toppingList = new ArrayList<>();
        String sql = "SELECT * FROM toppings ORDER BY toppingname";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int toppingsID = rs.getInt("toppingsID");
                String toppingname = rs.getString("toppingname");
                int toppingprice = rs.getInt("toppingprice");
                toppingList.add(new Toppings(toppingsID, toppingname, toppingprice));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i toppingsmapper", e.getMessage());
        }
        return toppingList;
    }
}