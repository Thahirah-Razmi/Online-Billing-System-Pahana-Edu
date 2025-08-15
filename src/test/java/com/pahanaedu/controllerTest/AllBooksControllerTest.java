package com.pahanaedu.controllerTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.controller.AllBooksController;
import com.pahanaedu.service.BookService;
import com.pahanaedu.model.Book;

public class AllBooksControllerTest {

    private AllBooksController controller;

    @Before
    public void setUp() {
        controller = new AllBooksController();
        controller.init(); // initialize the controller
    }

    @Test
    public void testInitSetsBookService() throws Exception {
        // We can reflectively check that bookService is not null
        java.lang.reflect.Field field = AllBooksController.class.getDeclaredField("bookService");
        field.setAccessible(true);
        BookService service = (BookService) field.get(controller);

        assertNotNull("BookService should be initialized in init()", service);
        System.out.println("BookService initialized: " + (service != null));
    }

    @Test
    public void testBookServiceReturnsAllBooks() throws Exception {
        java.lang.reflect.Field field = AllBooksController.class.getDeclaredField("bookService");
        field.setAccessible(true);
        BookService service = (BookService) field.get(controller);

        List<Book> books = service.getAllBooks();
        assertNotNull("getAllBooks() should return a non-null list", books);
        System.out.println("AllBooksController fetched books: " + books.size());
    }

    @Test
    public void testBookServiceSearchBooks() throws Exception {
        java.lang.reflect.Field field = AllBooksController.class.getDeclaredField("bookService");
        field.setAccessible(true);
        BookService service = (BookService) field.get(controller);

        List<Book> result = service.searchBooks("Clean Code");
        assertNotNull("searchBooks() should return a non-null list", result);
        System.out.println("AllBooksController search returned: " + result.size());
    }
}
