package com.pahana.adminstaffsystem.dao;

import com.pahana.adminstaffsystem.model.Staff;
import com.pahana.adminstaffsystem.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StaffDAO {

    // Mehtod to get All staff members from the db
    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();

        String query = "SELECT * FROM staff";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff();
                staff.setStaff_id(rs.getInt("staff_id"));
                staff.setUsername(rs.getString("username"));
                staff.setEmail(rs.getString("email"));
                staff.setPassword(rs.getString("password"));

                staffList.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //Method to add a staff member 
    public boolean addStaff(Staff staff) {
        if(staff == null) return false;
        String query = "INSERT INTO staff (username, email, password) VALUES (?,?,?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getUsername());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Staff getStaffById(int staffId) {
        String query = "SELECT * FROM staff WHERE staff_id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Staff staff = new Staff();
                staff.setUsername(rs.getString("username"));
                staff.setEmail(rs.getString("email"));
                staff.setPassword(rs.getString("password"));
                return staff;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateStaff(Staff staff, int staffId) {
        if(staff == null) return false;
        String query = "UPDATE staff SET username = ?, email = ?, password = ? WHERE staff_id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, staff.getUsername());
            stmt.setString(2, staff.getEmail());
            stmt.setString(3, staff.getPassword());
            stmt.setInt(4, staffId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStaffById(int staffId) {
        String query = "DELETE FROM staff WHERE staff_id = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUsernameExists(String username) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT staff_id FROM staff WHERE username = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isEmailExists(String email) {
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT staff_id FROM staff WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}