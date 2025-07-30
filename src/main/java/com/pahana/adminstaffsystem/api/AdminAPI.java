package com.pahana.adminstaffsystem.api;

import com.google.gson.Gson;
import com.pahana.adminstaffsystem.model.Admin;
import com.pahana.adminstaffsystem.service.AdminService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminAPI {

    private final AdminService adminService = new AdminService();
    private final Gson gson = new Gson();

    // POST - Login Admin
    @POST
    @Path("/login")
    public Response loginAdmin(String adminJson) {
        Admin adminRequest = gson.fromJson(adminJson, Admin.class);
        boolean isValid = adminService.login(adminRequest.getUsername(), adminRequest.getPassword());

        if (isValid) {
            Admin admin = adminService.getAdmin(adminRequest.getUsername());
            String json = gson.toJson(new LoginResponse(true, "Login successful", admin.getUsername()));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } else {
            String json = gson.toJson(new LoginResponse(false, "Invalid username or password", null));
            return Response.status(Response.Status.UNAUTHORIZED)
                           .entity(json)
                           .type(MediaType.APPLICATION_JSON)
                           .build();
        }
    }

    // Inner class to hold response JSON structure
    private static class LoginResponse {
        boolean success;
        String message;
        String username;

        public LoginResponse(boolean success, String message, String username) {
            this.success = success;
            this.message = message;
            this.username = username;
        }
    }
}
