/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pahana.adminstaffsystem.resources;

import com.pahana.adminstaffsystem.api.AdminAPI;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import com.pahana.adminstaffsystem.api.StaffAPI;


/**
 *
 * @author musharraf
 */

@ApplicationPath("/api") // All endpoints start with /api

public class ApplicationConfig extends Application{
    // No need to override anything for basic setup
    
      @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(StaffAPI.class);
        resources.add(AdminAPI.class);
        resources.add(com.pahana.adminstaffsystem.filters.CORSFilter.class);
        resources.add(JakartaEE10Resource.class);
        return resources;
    }
    
    
    
}
