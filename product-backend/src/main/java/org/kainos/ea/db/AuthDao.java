package org.kainos.ea.db;

import org.kainos.ea.cli.Token;
import org.kainos.ea.cli.RequestUser;
import org.kainos.ea.cli.User;
import org.kainos.ea.client.FailedToGetTokenException;
import org.kainos.ea.client.FailedToGetUserException;
import org.kainos.ea.client.FailedToInsertTokenException;
import org.kainos.ea.utils.PasswordHasher;

import java.sql.*;


public class AuthDao extends DatabaseConnector {

    public int createNewUser(RequestUser user) throws SQLException {
        Connection conn = getConnection();

        String query = "INSERT INTO `User` (email, password, role) VALUES (?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getEmail());
        statement.setString(2, PasswordHasher.hashPassword(user.getPassword()));
        statement.setString(3, user.getRole());

        statement.executeUpdate();
        ResultSet result = statement.getGeneratedKeys();

        if (result.next()) {
            return result.getInt(1);
        }
        return -1;
    }


    // WILL BE USED WITH AUTHORISATION TICKET //
    public Token getToken(String email) throws FailedToGetTokenException {
        try (Connection con = DatabaseConnector.getConnection()) {
            String query = "SELECT id, email, token, expiry FROM Tokens WHERE email = ?;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            Token token = null;
            while (rs.next()) {
                token = new Token(rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("token"),
                        rs.getTimestamp("expiry")
                );
            }
            return token;
        } catch (SQLException e) {
            throw new FailedToGetTokenException();
        }
    }

    public User getUser(String email) throws FailedToGetUserException {
        try (Connection con = DatabaseConnector.getConnection()) {
            String query = "SELECT id, email, password, role FROM `User` WHERE email = ? ;";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();

            User user = null;
            while (rs.next()) {
                user = new User(rs.getInt("id"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("role")
                );
            }
            return user;
        } catch (SQLException e) {
            throw new FailedToGetUserException();
        }
    }

}
