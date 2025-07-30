package com.pahana.adminstaffsystem.service;

import com.pahana.adminstaffsystem.dao.AdminDAO;
import com.pahana.adminstaffsystem.model.Admin;

public class AdminService {

    private AdminDAO adminDAO;

    public AdminService() {
        this.adminDAO = new AdminDAO();
    }

    // Validate login by checking username and password
    public boolean login(String username, String password) {
        return adminDAO.validateLogin(username, password);
    }

    // Get admin details by username (optional use after login)
    public Admin getAdmin(String username) {
        return adminDAO.getAdminByUsername(username);
    }
}
