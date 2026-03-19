package com.ec9590.sims.dao;

import com.ec9590.sims.model.User;
import java.sql.*;

public class UserDAO {

    /**
     * Validates credentials against the users table.
     * Returns the User object if valid, null otherwise.
     */
    public User validateUser(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null; // invalid credentials
    }
}
