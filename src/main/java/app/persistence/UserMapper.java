package app.persistence;

import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper
{

    public static User login(String email, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "select * from users where email=? and password=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int userID = rs.getInt("userID");
                String role = rs.getString("role");
                int wallet = rs.getInt("wallet");
                return new User(userID, email, password, role, wallet);
            } else
            {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static void createuser(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "insert into users (email, password) values (?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, password);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        }
        catch (SQLException e)
        {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static void updateWallet(String email, int newWalletBalance, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "update users set wallet = ? where email = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, newWalletBalance);
            ps.setString(2, email);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved opdatering af wallet balance");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Databasefejl under opdatering af wallet balance");
        }
    }
}