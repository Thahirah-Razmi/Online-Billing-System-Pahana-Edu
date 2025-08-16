package com.pahanaedu.daoTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pahanaedu.dao.BookDAO;
import com.pahanaedu.model.Book;

public class BookDAOTest {

    private static BookDAO bookDAO;
    private static Book testBook; // static for @BeforeClass
    private static int testBookId;

    private static final String TEST_TITLE = "The Hobbit";
    private static final String TEST_LANGUAGE = "English";

    @BeforeClass
    public static void setUpClass() throws SQLException {
        bookDAO = new BookDAO();

        // Check if the test book already exists
        List<Book> existing = bookDAO.searchBooks(TEST_TITLE.toLowerCase());
        if (!existing.isEmpty()) {
            testBook = existing.get(0);
            testBookId = testBook.getId();
            System.out.println("Test book already exists with ID: " + testBookId);
            return;
        }

        // Insert the single test book
        testBook = new Book();
        testBook.setTitle(TEST_TITLE);
        testBook.setCategory("Fantasy");
        testBook.setAuthor("J.R.R. Tolkien");
        testBook.setLanguage(TEST_LANGUAGE);
        testBook.setPrice(9400.00);

        bookDAO.addBook(testBook);  // no boolean assignment

        // Retrieve ID
        List<Book> books = bookDAO.searchBooks(TEST_TITLE.toLowerCase());
        testBookId = books.get(0).getId();
        System.out.println("Inserted test book with ID: " + testBookId);
    }

    //@AfterClass
    //public static void tearDownClass() throws SQLException {
    //    if (testBookId > 0) {
    //        boolean deleted = bookDAO.deleteBook(testBookId);
    //        System.out.println("Deleted test book ID " + testBookId + ": " + deleted);
    //    }
    //}

    @Test
    public void testAddBook() throws SQLException {
        // Create a temporary book to test addition
        Book tempBook = new Book();
        tempBook.setTitle("1984");
        tempBook.setCategory("Dystopian");
        tempBook.setAuthor("George Orwell");
        tempBook.setLanguage("English");
        tempBook.setPrice(1500.00);

        // Add the book
        bookDAO.addBook(tempBook);

        // Verify the book was added by searching
        List<Book> books = bookDAO.searchBooks(tempBook.getTitle().toLowerCase());
        assertFalse("Book should exist after add", books.isEmpty());
        Book addedBook = books.get(0);
        assertEquals("1984", addedBook.getTitle());
        assertEquals("George Orwell", addedBook.getAuthor());
        assertEquals("Dystopian", addedBook.getCategory());
        assertEquals("English", addedBook.getLanguage());
        assertEquals(1500.00, addedBook.getPrice(), 0.001);

        System.out.println("AddBook test passed: " + addedBook.getTitle() + ", ID: " + addedBook.getId());

        // Clean up: delete the temporary book
        //bookDAO.deleteBook(addedBook.getId());
    }

    @Test
    public void testGetBookById() throws SQLException {
        Book fetched = bookDAO.getBookById(testBookId);
        assertNotNull(fetched);
        assertEquals(TEST_TITLE, fetched.getTitle());
        System.out.println("GetBookById: " + fetched.getTitle() + ", Price: " + fetched.getPrice());
    }

    @Test
    public void testGetAllBooksContainsTestBook() throws SQLException {
        List<Book> books = bookDAO.getAllBooks();
        boolean found = books.stream().anyMatch(b -> b.getId() == testBookId);
        assertTrue(found);
        System.out.println("Verified test book exists in all books.");
    }

    @Test
    public void testSearchBooks() throws SQLException {
        List<Book> books = bookDAO.searchBooks(TEST_TITLE.toLowerCase());
        assertFalse(books.isEmpty());
        assertEquals(TEST_TITLE, books.get(0).getTitle());
        System.out.println("Search result: " + books.get(0).getTitle());
    }

    @Test
    public void testIsBookNameOrLanguageExists() throws SQLException {
        Book tempBook = new Book();
        tempBook.setTitle("To Kill a Mockingbird");
        tempBook.setCategory("Classic Literature");
        tempBook.setAuthor("Harper Lee");
        tempBook.setLanguage("English");
        tempBook.setPrice(1200.00);

        bookDAO.addBook(tempBook);

        boolean exists = bookDAO.isBookNameOrLanguageExists(
                tempBook.getTitle().toLowerCase(),
                tempBook.getLanguage().toLowerCase()
        );
        assertTrue("Book should exist", exists);
        System.out.println("Book existence check passed for: " + tempBook.getTitle() + ", " + tempBook.getLanguage());

        // Clean up
        List<Book> books = bookDAO.searchBooks(tempBook.getTitle().toLowerCase());
        if (!books.isEmpty()) {
            bookDAO.deleteBook(books.get(0).getId());
        }
    }

    @Test
    public void testUpdateBook() throws SQLException {
        Book fetched = bookDAO.getBookById(testBookId);

        fetched.setTitle("The Hobbit: Illustrated Edition");
        fetched.setPrice(10000.00);

        bookDAO.updateBook(fetched);

        Book updated = bookDAO.getBookById(testBookId);
        assertEquals("The Hobbit: Illustrated Edition", updated.getTitle());
        assertEquals(10000.00, updated.getPrice(), 0.001);
        System.out.println("Book updated: " + updated.getTitle() + ", Price: " + updated.getPrice());
    }

    @Test
    public void testDeleteBook() throws SQLException {
        // Add a temporary book for deletion test
        Book tempBook = new Book();
        tempBook.setTitle("Pride and Prejudice");
        tempBook.setCategory("Romance");
        tempBook.setAuthor("Jane Austen");
        tempBook.setLanguage("English");
        tempBook.setPrice(1000.00);

        bookDAO.addBook(tempBook);

        // Fetch its ID
        List<Book> books = bookDAO.searchBooks(tempBook.getTitle().toLowerCase());
        assertFalse("Temp book should exist after add", books.isEmpty());
        int tempBookId = books.get(0).getId();

        // Delete the book (void method)
        bookDAO.deleteBook(tempBookId);
        System.out.println("Book deleted successfully, ID: " + tempBookId);

        // Verify deletion
        Book fetched = bookDAO.getBookById(tempBookId);
        assertNull("Book should not exist after deletion", fetched);
    }
}
