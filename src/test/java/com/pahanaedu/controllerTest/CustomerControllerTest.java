package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.CustomerController;
import com.pahanaedu.service.CustomerService;

public class CustomerControllerTest {

    private CustomerController controller;

    @Before
    public void setUp() {
        controller = new CustomerController();
        controller.init(); // initialize services
    }

    @Test
    public void testInitServices() throws Exception {
        // Use reflection to access private fields
        Field customerServiceField = CustomerController.class.getDeclaredField("customerService");
        customerServiceField.setAccessible(true);

        CustomerService customerService = (CustomerService) customerServiceField.get(controller);

        assertNotNull("CustomerService should be initialized", customerService);

        System.out.println("CustomerService initialized: " + (customerService != null));
    }
}
