package com.pahana.adminstaffsystem.dao;

import com.pahana.adminstaffsystem.model.Staff;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StaffDAOTest {

    private StaffDAO dao;
    private Integer createdStaffId = null;

    @BeforeAll
    public void setUpClass() {
        dao = new StaffDAO();
    }

    @AfterAll
    public void tearDownClass() {
        // Optionally cleanup any lingering test staff records
    }

    @BeforeEach
    public void setUp() {
        createdStaffId = null;
    }

    @AfterEach
    public void tearDown() {
        // Remove test staff member after each test
        if (createdStaffId != null) {
            dao.deleteStaffById(createdStaffId);
        }
    }

    @Test
    public void testGetAllStaff() {
        List<Staff> staffList = dao.getAllStaff();
        assertNotNull(staffList, "Staff list should not be null.");
        // Optionally check for existence or structure
    }

    @Test
    public void testAddStaff_success() {
        Staff staff = new Staff();
        staff.setUsername("junit_staff");
        staff.setEmail("junit_staff@example.com");
        staff.setPassword("testpass");

        boolean result = dao.addStaff(staff);
        assertTrue(result, "Should successfully add staff.");

        // Find inserted staff for cleanup
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "junit_staff".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted, "Inserted staff should exist.");
        createdStaffId = inserted.getStaff_id(); // For cleanup
    }

    @Test
    public void testAddStaff_nullStaff() {
        boolean result = dao.addStaff(null);
        assertFalse(result, "Adding null staff should fail.");
    }

    @Test
    public void testGetStaffById_existing() {
        // Add staff first
        Staff staff = new Staff();
        staff.setUsername("findme_staff");
        staff.setEmail("findme_staff@example.com");
        staff.setPassword("passfind");
        boolean added = dao.addStaff(staff);
        assertTrue(added);
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "findme_staff".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted);
        int staffId = inserted.getStaff_id();
        createdStaffId = staffId;

        Staff result = dao.getStaffById(staffId);
        assertNotNull(result, "Should get staff by id.");
        assertEquals("findme_staff", result.getUsername());
    }

    @Test
    public void testGetStaffById_nonExisting() {
        Staff result = dao.getStaffById(-99999);
        assertNull(result, "Non-existing staff id should return null.");
    }

    @Test
    public void testUpdateStaff_success() {
        // Add staff first
        Staff staff = new Staff();
        staff.setUsername("update_staff");
        staff.setEmail("update_staff@example.com");
        staff.setPassword("updatepass");
        boolean added = dao.addStaff(staff);
        assertTrue(added);
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "update_staff".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted);
        int staffId = inserted.getStaff_id();
        createdStaffId = staffId;

        // Update values
        Staff updatedStaff = new Staff();
        updatedStaff.setUsername("updated_staff");
        updatedStaff.setEmail("updated_staff@example.com");
        updatedStaff.setPassword("updatedpass");

        boolean updated = dao.updateStaff(updatedStaff, staffId);
        assertTrue(updated, "Staff should be updated.");

        Staff fetched = dao.getStaffById(staffId);
        assertEquals("updated_staff", fetched.getUsername());
        assertEquals("updated_staff@example.com", fetched.getEmail());
        assertEquals("updatedpass", fetched.getPassword());
    }

    @Test
    public void testUpdateStaff_invalid() {
        boolean result = dao.updateStaff(null, -1);
        assertFalse(result, "Updating with null staff should fail.");
    }

    @Test
    public void testDeleteStaffById_success() {
        // Add staff first
        Staff staff = new Staff();
        staff.setUsername("delete_staff");
        staff.setEmail("delete_staff@example.com");
        staff.setPassword("deletepass");
        boolean added = dao.addStaff(staff);
        assertTrue(added);
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "delete_staff".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted);
        int staffId = inserted.getStaff_id();

        boolean deleted = dao.deleteStaffById(staffId);
        assertTrue(deleted, "Staff should be deleted.");

        Staff fetched = dao.getStaffById(staffId);
        assertNull(fetched, "Deleted staff should not be found.");
    }

    @Test
    public void testDeleteStaffById_nonExisting() {
        boolean deleted = dao.deleteStaffById(-99999);
        assertFalse(deleted, "Deleting non-existing staff should fail.");
    }

    @Test
    public void testIsUsernameExists_true() {
        // Add staff
        Staff staff = new Staff();
        staff.setUsername("exists_staff");
        staff.setEmail("exists_staff@example.com");
        staff.setPassword("existspass");
        boolean added = dao.addStaff(staff);
        assertTrue(added);
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "exists_staff".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted);
        createdStaffId = inserted.getStaff_id();

        boolean exists = dao.isUsernameExists("exists_staff");
        assertTrue(exists, "Username should exist.");
    }

    @Test
    public void testIsUsernameExists_false() {
        boolean exists = dao.isUsernameExists("definitely_not_exist");
        assertFalse(exists, "Non-existing username should not exist.");
    }

    @Test
    public void testIsEmailExists_true() {
        // Add staff
        Staff staff = new Staff();
        staff.setUsername("exists_email");
        staff.setEmail("exists_email@example.com");
        staff.setPassword("existsemailpass");
        boolean added = dao.addStaff(staff);
        assertTrue(added);
        List<Staff> staffList = dao.getAllStaff();
        Staff inserted = staffList.stream()
                .filter(s -> "exists_email".equals(s.getUsername()))
                .findFirst().orElse(null);
        assertNotNull(inserted);
        createdStaffId = inserted.getStaff_id();

        boolean exists = dao.isEmailExists("exists_email@example.com");
        assertTrue(exists, "Email should exist.");
    }

    @Test
    public void testIsEmailExists_false() {
        boolean exists = dao.isEmailExists("definitely_not_exist@example.com");
        assertFalse(exists, "Non-existing email should not exist.");
    }
}