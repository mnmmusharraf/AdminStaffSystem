package com.pahana.adminstaffsystem.api;

import com.google.gson.Gson;
import com.pahana.adminstaffsystem.model.Staff;
import com.pahana.adminstaffsystem.service.StaffService;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffAPI {

    private final StaffService staffService = new StaffService();
    private final Gson gson = new Gson();

    // GET all staff
    @GET
    public Response getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        String json = gson.toJson(staffList);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    // GET one staff by ID
    @GET
    @Path("/{id}")
    public Response getStaffById(@PathParam("id") int id) {
        Staff staff = staffService.getStaffById(id);
        if (staff != null) {
            String json = gson.toJson(staff);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson("Staff not found"))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    // POST new staff
    @POST
    public Response createStaff(String staffJson) {
        Staff staff = gson.fromJson(staffJson, Staff.class);
        boolean success = staffService.addStaff(staff);
        if (success) {
            return Response.status(Response.Status.CREATED)
                    .entity(gson.toJson("Staff added successfully"))
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Failed to add staff"))
                    .build();
        }
    }

    // PUT (update) staff
    @PUT
    @Path("/{id}")
    public Response updateStaff(@PathParam("id") int id, String staffJson) {
        Staff staff = gson.fromJson(staffJson, Staff.class);
        boolean success = staffService.updateStaff(staff, id);
        if (success) {
            return Response.ok(gson.toJson("Staff updated successfully")).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(gson.toJson("Failed to update staff"))
                    .build();
        }
    }

    // DELETE staff
    @DELETE
    @Path("/{id}")
    public Response deleteStaff(@PathParam("id") int id) {
        boolean success = staffService.deleteStaff(id);
        if (success) {
            return Response.ok(gson.toJson("Staff deleted successfully")).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson("Staff not found"))
                    .build();
        }
    }

    // GET - Check if username or email already exists
    @GET
    @Path("/check-unique")
    public Response checkUnique(@QueryParam("username") String username, @QueryParam("email") String email) {
        boolean usernameExists = staffService.isUsernameExists(username);
        boolean emailExists = staffService.isEmailExists(email);

        String json = gson.toJson(new UniqueCheckResponse(usernameExists, emailExists));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

// Inner class to hold the response structure
    private static class UniqueCheckResponse {

        boolean usernameExists;
        boolean emailExists;

        public UniqueCheckResponse(boolean usernameExists, boolean emailExists) {
            this.usernameExists = usernameExists;
            this.emailExists = emailExists;
        }
    }

}
