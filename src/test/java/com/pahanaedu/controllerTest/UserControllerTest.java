package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.UserController;
import com.pahanaedu.service.UserService;

public class UserControllerTest {

    private UserController controller;

    @Before
    public void setUp() throws Exception {
        controller = new UserController();
        controller.init(); // initialize userService
    }

    @Test
    public void testInitUserService() throws Exception {
        // Use reflection to access private field
        Field userServiceField = UserController.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);

        UserService userService = (UserService) userServiceField.get(controller);

        assertNotNull("UserService should be initialized", userService);

        System.out.println("UserService initialized: " + (userService != null));
    }
}
