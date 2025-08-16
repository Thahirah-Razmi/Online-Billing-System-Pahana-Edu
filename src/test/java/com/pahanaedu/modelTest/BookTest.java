package com.pahanaedu.modelTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.pahanaedu.model.Book;

public class BookTest {

    private Book book;

    @Before
    public void setUp() {
        book = new Book();
    }

    @Test
    public void testSettersAndGetters() {
        book.setId(6);
        book.setTitle("The Art of Testing");
        book.setCategory("Software");
        book.setAuthor("Alice Johnson");
        book.setLanguage("English");
        book.setPrice(2500.00);

        assertEquals("ID mismatch", 6, book.getId());
        assertEquals("Title mismatch", "The Art of Testing", book.getTitle());
        assertEquals("Category mismatch", "Software", book.getCategory());
        assertEquals("Author mismatch", "Alice Johnson", book.getAuthor());
        assertEquals("Language mismatch", "English", book.getLanguage());
        assertEquals("Price mismatch", 2500.00, book.getPrice(), 0.001);

        System.out.println("[PASS] Book getters/setters test passed for title: " + book.getTitle());
    }

    @Test
    public void testParameterizedConstructor() {
        Book book2 = new Book(7, "Mastering Algorithms", "Computer Science", "Bob Smith", "English", 3200.00);

        assertEquals("ID mismatch", 7, book2.getId());
        assertEquals("Title mismatch", "Mastering Algorithms", book2.getTitle());
        assertEquals("Category mismatch", "Computer Science", book2.getCategory());
        assertEquals("Author mismatch", "Bob Smith", book2.getAuthor());
        assertEquals("Language mismatch", "English", book2.getLanguage());
        assertEquals("Price mismatch", 3200.00, book2.getPrice(), 0.001);

        System.out.println("[PASS] Book parameterized constructor test passed for title: " + book2.getTitle());
    }
}
