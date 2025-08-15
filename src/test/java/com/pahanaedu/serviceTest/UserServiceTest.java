package com.pahanaedu.serviceTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest {

    private static UserService userService = UserService.getInstance();
    private static int testUserId;
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_EMAIL = "testuser@example.com";

    @Test
    public void test1_AddUser() throws SQLException {
        User user = new User();
        user.setUsername(TEST_USERNAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword("password123");
        user.setRole("Staff");

        userService.addUser(user);

        User retrieved = userService.getUserByUsername(TEST_USERNAME);
        assertNotNull("User should be added and retrievable by username", retrieved);

        testUserId = retrieved.getId();
        System.out.println("Added User ID: " + testUserId);
    }

    @Test
    public void test2_ExistenceChecks() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            assertNotNull("No test user found.", retrieved);
            testUserId = retrieved.getId();
        }

        assertTrue("Email should exist", userService.isEmailExists(TEST_EMAIL));
        assertTrue("Username should exist", userService.isUsernameExists(TEST_USERNAME));
        assertTrue("Username or Email should exist", userService.isUsernameOrEmailExists(TEST_USERNAME, TEST_EMAIL));

        assertFalse("Nonexistent email should not exist", userService.isEmailExists("nonexist@example.com"));
        assertFalse("Nonexistent username should not exist", userService.isUsernameExists("nonexistuser"));
        assertFalse("Nonexistent username/email should not exist", userService.isUsernameOrEmailExists("nonexistuser", "nonexist@example.com"));

        System.out.println("Existence checks completed for test user: " + testUserId);
    }

    @Test
    public void test3_GetUserById() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            assertNotNull("No test user found.", retrieved);
            testUserId = retrieved.getId();
            System.out.println("Retrieved User ID: " + testUserId);
        }

        User user = userService.getUserById(testUserId);
        assertNotNull("User should be retrievable by ID", user);
        assertEquals(TEST_USERNAME, user.getUsername());
        System.out.println("User retrieved by ID successfully: " + testUserId);
    }

    @Test
    public void test4_GetAllUsers() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            assertNotNull("No test user found.", retrieved);
            testUserId = retrieved.getId();
        }

        List<User> users = userService.getAllUsers();
        assertNotNull("All users list should not be null", users);
        boolean found = users.stream().anyMatch(u -> u.getId() == testUserId);
        assertTrue("All users should contain the test user", found);
        System.out.println("All users retrieved. Test user found: " + testUserId);
    }

    @Test
    public void test5_GetUserByUsername() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            assertNotNull("No test user found.", retrieved);
            testUserId = retrieved.getId();
        }

        User user = userService.getUserByUsername(TEST_USERNAME);
        assertNotNull("User should be retrievable by username", user);
        assertEquals(testUserId, user.getId());
        System.out.println("User retrieved by username successfully: " + testUserId);
    }

    @Test
    public void test6_UpdateUser() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            assertNotNull("No test user found.", retrieved);
            testUserId = retrieved.getId();
        }

        User user = userService.getUserById(testUserId);
        assertNotNull(user);

        user.setPassword("newpassword123");
        user.setRole("Admin");
        userService.updateUser(user);

        User updated = userService.getUserById(testUserId);
        assertEquals("newpassword123", updated.getPassword());
        assertEquals("Admin", updated.getRole());
        System.out.println("User updated successfully: " + testUserId);
    }

    @Test
    public void test7_DeleteUser() throws SQLException {
        if (testUserId == 0) {
            User retrieved = userService.getUserByUsername(TEST_USERNAME);
            if (retrieved == null) {
                System.out.println("No user to delete. Skipping delete test.");
                return;
            }
            testUserId = retrieved.getId();
        }

        userService.deleteUser(testUserId);
        User user = userService.getUserById(testUserId);
        assertNull("User should be deleted", user);
        System.out.println("User deleted successfully: " + testUserId);

        testUserId = 0; // reset
    }
}
