package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.LogoutController;

public class LogoutControllerTest {

    private LogoutController controller;

    @Before
    public void setUp() {
        controller = new LogoutController();
    }

    @Test
    public void testControllerInstantiation() {
        assertNotNull("LogoutController should be instantiated", controller);
        System.out.println("LogoutController instantiated successfully: " + (controller != null));
    }
}
