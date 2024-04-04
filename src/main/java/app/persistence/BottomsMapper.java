package app.persistence;

import app.entities.Bottoms;
import app.entities.Task;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BottomsMapper {

    public static List<Bottoms> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        List<Bottoms> bottomsList = new ArrayList<>();

        String sql = "select * from bottoms ORDER BY bottomname";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int bottomsID = rs.getInt("bottomsID");
                String bottomsName = rs.getString("bottomname");
                int bottomsPrice = rs.getInt("bottomprice");

                Bottoms bottoms = new Bottoms(bottomsID, bottomsName, bottomsPrice);
                bottomsList.add(bottoms);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl!!!!", e.getMessage());
        }
        return bottomsList;
    }
}