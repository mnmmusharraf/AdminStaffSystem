package com.pahana.adminstaffsystem.dao;

import com.pahana.adminstaffsystem.model.Admin;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminDAOTest {

    private AdminDAO dao;

    @BeforeAll
    public void setUpClass() {
        dao = new AdminDAO();
        
    }

    @AfterAll
    public void tearDownClass() {
        
    }

    @BeforeEach
    public void setUp() {
         dao = new AdminDAO();
    }

    @AfterEach
    public void tearDown() {
        // No teardown needed for each test in this case
    }

    @Test
    public void testValidateLogin_withValidCredentials() {
        boolean result = dao.validateLogin("admin", "admin123");
        assertTrue(result, "Login should succeed with valid credentials");
    }

    @Test
    public void testValidateLogin_withInvalidCredentials() {
        boolean result = dao.validateLogin("wronguser", "wrongpass");
        assertFalse(result, "Login should fail with invalid credentials");
    }

    @Test
    public void testValidateLogin_withWrongPassword() {
        boolean result = dao.validateLogin("admin", "wrongpass");
        assertFalse(result, "Login should fail with wrong password");
    }

    @Test
    public void testValidateLogin_withNullOrEmpty() {
        assertFalse(dao.validateLogin("", ""), "Login should fail with empty credentials");
        assertFalse(dao.validateLogin(null, null), "Login should fail with null credentials");
    }

    @Test
    public void testGetAdminByUsername_existingUser() {
        Admin admin = dao.getAdminByUsername("admin");
        assertNotNull(admin, "Should return Admin object for existing user");
        assertEquals("admin", admin.getUsername());
        assertEquals("admin123", admin.getPassword());
    }

    @Test
    public void testGetAdminByUsername_nonExistingUser() {
        Admin admin = dao.getAdminByUsername("nouser");
        assertNull(admin, "Should return null for non-existing user");
    }

    @Test
    public void testGetAdminByUsername_nullOrEmpty() {
        assertNull(dao.getAdminByUsername(""), "Should return null for empty username");
        assertNull(dao.getAdminByUsername(null), "Should return null for null username");
    }
}