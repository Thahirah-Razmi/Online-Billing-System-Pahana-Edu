package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.BillController;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.BookService;
import com.pahanaedu.service.CustomerService;

public class BillControllerTest {

    private BillController controller;

    @Before
    public void setUp() {
        controller = new BillController();
        controller.init(); // initialize services
    }

    @Test
    public void testInitServices() throws Exception {
        // Use reflection to access private fields
        Field billServiceField = BillController.class.getDeclaredField("billService");
        Field customerServiceField = BillController.class.getDeclaredField("customerService");
        Field bookServiceField = BillController.class.getDeclaredField("bookService");

        billServiceField.setAccessible(true);
        customerServiceField.setAccessible(true);
        bookServiceField.setAccessible(true);

        BillService billService = (BillService) billServiceField.get(controller);
        CustomerService customerService = (CustomerService) customerServiceField.get(controller);
        BookService bookService = (BookService) bookServiceField.get(controller);

        assertNotNull("BillService should be initialized", billService);
        assertNotNull("CustomerService should be initialized", customerService);
        assertNotNull("BookService should be initialized", bookService);

        System.out.println("BillService initialized: " + (billService != null));
        System.out.println("CustomerService initialized: " + (customerService != null));
        System.out.println("BookService initialized: " + (bookService != null));
    }
}
