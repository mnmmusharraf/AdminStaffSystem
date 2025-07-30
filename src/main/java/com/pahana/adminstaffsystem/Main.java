/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pahana.adminstaffsystem;

import com.pahana.adminstaffsystem.db.DBConnection;
import java.sql.Connection;

/**
 *
 * @author musharraf
 */
public class Main {
    
    public static void main(String[] args) {
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Connection success.");
        } else {
            System.out.println("Connection failed.");
        }
    }
    
}
