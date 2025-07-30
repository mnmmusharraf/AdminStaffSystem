/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pahana.adminstaffsystem.service;

import com.pahana.adminstaffsystem.dao.StaffDAO;
import com.pahana.adminstaffsystem.model.Staff;

import java.util.List;

/**
 *
 * @author musharraf
 */
public class StaffService {

    private final StaffDAO staffDAO;

    public StaffService() {
        this.staffDAO = new StaffDAO();
    }

    // Get all staff members
    public List<Staff> getAllStaff() {
        return staffDAO.getAllStaff();
    }

    // Add a new staff member
    public boolean addStaff(Staff staff) {
        // You can add validation here if needed
        return staffDAO.addStaff(staff);
    }

    // Get a staff member by ID
    public Staff getStaffById(int staffId) {
        return staffDAO.getStaffById(staffId);
    }

    // Update a staff member
    public boolean updateStaff(Staff staff, int staffId) {
        return staffDAO.updateStaff(staff, staffId);
    }

    // Delete a staff member
    public boolean deleteStaff(int staffId) {
        return staffDAO.deleteStaffById(staffId);
    }

    public boolean isUsernameExists(String username) {
        return staffDAO.isUsernameExists(username);
    }

    public boolean isEmailExists(String email) {
        return staffDAO.isEmailExists(email);
    }

}
