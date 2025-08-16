package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pahanaedu.dao.UserDAO;
import com.pahanaedu.model.User;

public class UserDAOTest {

    private static UserDAO userDAO;
    private static User testUser;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        userDAO = new UserDAO();
        testUser = new User();
        testUser.setUsername("parker");
        testUser.setEmail("parker@gmail.com");
        testUser.setPassword("Parker@123");
        testUser.setRole("Cashier");

        // Add the user only if it doesn't exist
        if (!userDAO.isUsernameExists(testUser.getUsername())) {
            userDAO.addUser(testUser);
            // Retrieve the user from DB to get the ID
            testUser = userDAO.getUserByUsername(testUser.getUsername());
            System.out.println("Test user added with ID: " + testUser.getId());
        } else {
            testUser = userDAO.getUserByUsername(testUser.getUsername());
            System.out.println("Test user already exists with ID: " + testUser.getId());
        }
    }

    //@AfterClass
    //public static void tearDownClass() throws SQLException {
    //    User existing = userDAO.getUserByUsername(testUser.getUsername());
    //    if (existing != null) {
    //        boolean deleted = userDAO.deleteUser(existing.getId());
    //        System.out.println("Test user deleted: " + deleted);
    //    }
    //}

    @Test
    public void testAddUser() throws SQLException {
        User newUser = new User();
        newUser.setUsername("tony");
        newUser.setEmail("tony@gmail.com");
        newUser.setPassword("Tony@123");
        newUser.setRole("Cashier");

        // Delete if already exists
        User existing = userDAO.getUserByUsername(newUser.getUsername());
        if (existing != null) {
            userDAO.deleteUser(existing.getId());
        }

        // Add user
        userDAO.addUser(newUser); // void method

        // Verify user exists
        User fetched = userDAO.getUserByUsername(newUser.getUsername());
        assertNotNull(fetched);
        assertEquals(newUser.getEmail(), fetched.getEmail());
        assertEquals(newUser.getRole(), fetched.getRole());

        System.out.println("User added successfully: " + fetched.getUsername());

        // Cleanup
        //userDAO.deleteUser(fetched.getId());
    }

    @Test
    public void testGetUserById() throws SQLException {
        User fetched = userDAO.getUserById(testUser.getId());
        assertNotNull(fetched);
        assertEquals(testUser.getUsername(), fetched.getUsername());
        System.out.println("Get by ID: " + fetched.getUsername());
    }

    @Test
    public void testGetUserByUsername() throws SQLException {
        User fetched = userDAO.getUserByUsername(testUser.getUsername());
        assertNotNull(fetched);
        assertEquals(testUser.getEmail(), fetched.getEmail());
        System.out.println("Get by username: " + fetched.getUsername() + ", Email: " + fetched.getEmail());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        testUser.setEmail("updatedparker@gmail.com");
        testUser.setRole("Manager");
        userDAO.updateUser(testUser);

        User updated = userDAO.getUserById(testUser.getId());
        assertEquals("updatedparker@gmail.com", updated.getEmail());
        assertEquals("Manager", updated.getRole());
        System.out.println("User updated: " + updated.getUsername() + ", Email: " + updated.getEmail() + ", Role: " + updated.getRole());
    }

    @Test
    public void testIsEmailExists() throws SQLException {
        assertTrue(userDAO.isEmailExists(testUser.getEmail()));
        assertFalse(userDAO.isEmailExists("nonexistent@example.com"));
        System.out.println("Email existence check passed");
    }

    @Test
    public void testIsUsernameExists() throws SQLException {
        assertTrue(userDAO.isUsernameExists(testUser.getUsername()));
        assertFalse(userDAO.isUsernameExists("nonexistentuser"));
        System.out.println("Username existence check passed");
    }

    @Test
    public void testIsUsernameOrEmailExists() throws SQLException {
        assertTrue(userDAO.isUsernameOrEmailExists(testUser.getUsername(), "noemail@example.com"));
        assertTrue(userDAO.isUsernameOrEmailExists("nouser", testUser.getEmail()));
        assertTrue(userDAO.isUsernameOrEmailExists(testUser.getUsername(), testUser.getEmail()));
        assertFalse(userDAO.isUsernameOrEmailExists("nouser", "noemail@example.com"));
        System.out.println("Username or email combined check passed");
    }

    @Test
    public void testGetAllUsers() throws SQLException {
        List<User> users = userDAO.getAllUsers();
        assertNotNull(users);
        boolean found = users.stream().anyMatch(u -> testUser.getUsername().equals(u.getUsername()));
        assertTrue(found);
        System.out.println("Verified test user exists in all users");
    }

    @Test
    public void testDeleteUser() throws SQLException {
        // Retrieve user again to ensure ID is present
        User fetched = userDAO.getUserByUsername(testUser.getUsername());
        assertNotNull("User must exist before deletion", fetched);

        userDAO.deleteUser(fetched.getId());
        System.out.println("User deleted successfully, ID: " + fetched.getId());

        User afterDelete = userDAO.getUserById(fetched.getId());
        assertNull("Deleted user should not exist", afterDelete);
    }
}
