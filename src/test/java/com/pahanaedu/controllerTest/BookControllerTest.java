package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.BookController;
import com.pahanaedu.service.BookService;

public class BookControllerTest {

    private BookController controller;

    @Before
    public void setUp() {
        controller = new BookController();
        controller.init(); // initialize services
    }

    @Test
    public void testInitServices() throws Exception {
        // Use reflection to access private field
        Field bookServiceField = BookController.class.getDeclaredField("bookService");
        bookServiceField.setAccessible(true);

        BookService bookService = (BookService) bookServiceField.get(controller);

        assertNotNull("BookService should be initialized", bookService);

        System.out.println("BookService initialized: " + (bookService != null));
    }
}
