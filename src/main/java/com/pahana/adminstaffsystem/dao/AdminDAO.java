package com.pahana.adminstaffsystem.dao;

import com.pahana.adminstaffsystem.db.DBConnection;
import com.pahana.adminstaffsystem.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    // Check if the username and password match an admin in the database
    public boolean validateLogin(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // In real apps, compare hashed password

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // login valid if row exists

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get the admin details if login is successful (optional)
    public Admin getAdminByUsername(String username) {
        String sql = "SELECT * FROM admin WHERE username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                return new Admin(username, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
