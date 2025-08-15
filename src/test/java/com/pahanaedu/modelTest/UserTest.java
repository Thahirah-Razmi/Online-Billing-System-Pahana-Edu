package com.pahanaedu.modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.model.User;

public class UserTest {

    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSettersAndGetters() {
        user.setId(6);
        user.setUsername("charles_kim");
        user.setEmail("charles.kim@example.com");
        user.setPassword("Charles@123");
        user.setRole("Manager");

        assertEquals("ID mismatch", 6, user.getId());
        assertEquals("Username mismatch", "charles_kim", user.getUsername());
        assertEquals("Email mismatch", "charles.kim@example.com", user.getEmail());
        assertEquals("Password mismatch", "Charles@123", user.getPassword());
        assertEquals("Role mismatch", "Manager", user.getRole());

        System.out.println("User getters/setters test passed: " + user.getUsername());
    }

    @Test
    public void testParameterizedConstructor() {
        User user2 = new User(7, "sophia_wong", "sophia.wong@example.com", "Sophia@123", "Cashier");

        assertEquals("ID mismatch", 7, user2.getId());
        assertEquals("Username mismatch", "sophia_wong", user2.getUsername());
        assertEquals("Email mismatch", "sophia.wong@example.com", user2.getEmail());
        assertEquals("Password mismatch", "Sophia@123", user2.getPassword());
        assertEquals("Role mismatch", "Cashier", user2.getRole());

        System.out.println("[PASS] User parameterized constructor test passed for username: " + user2.getUsername());
    }
}
