package com.pahanaedu.serviceTest;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.pahanaedu.model.Book;
import com.pahanaedu.service.BookService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookServiceTest {

    private static BookService bookService = BookService.getInstance();
    private static int testBookId;
    private static final String TEST_TITLE = "JUnit Test Book";

    @Test
    public void test1_AddBook() throws SQLException {
        Book book = new Book();
        book.setTitle(TEST_TITLE);
        book.setAuthor("Test Author");
        book.setLanguage("English");
        book.setPrice(50.0);

        bookService.addBook(book);
        System.out.println("Book added successfully");

        List<Book> books = bookService.searchBooks(TEST_TITLE);
        assertFalse("Book should be added", books.isEmpty());

        testBookId = books.get(0).getId();
        System.out.println("Added Book ID: " + testBookId);
    }

    @Test
    public void test2_GetBookById() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            assertFalse("No test book found. Run test1_AddBook first or add a setup book.", books.isEmpty());
            testBookId = books.get(0).getId();
            System.out.println("Retrieved Book ID: " + testBookId);
        }

        Book book = bookService.getBookById(testBookId);
        assertNotNull("Book should exist by ID", book);
        assertEquals(TEST_TITLE, book.getTitle());
        System.out.println("Book retrieved by ID successfully: " + book.getId());
    }

    @Test
    public void test3_GetAllBooks() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            assertFalse("No test book found. Run test1_AddBook first or add a setup book.", books.isEmpty());
            testBookId = books.get(0).getId();
        }

        List<Book> books = bookService.getAllBooks();
        assertNotNull("All books list should not be null", books);
        boolean found = books.stream().anyMatch(b -> b.getId() == testBookId);
        assertTrue("All books should contain the test book", found);
        System.out.println("All books retrieved. Test book found: " + testBookId);
    }

    @Test
    public void test4_SearchBooks() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            assertFalse("No test book found. Run test1_AddBook first or add a setup book.", books.isEmpty());
            testBookId = books.get(0).getId();
        }

        List<Book> books = bookService.searchBooks("JUnit");
        assertFalse("Search should return at least one book", books.isEmpty());
        boolean found = books.stream().anyMatch(b -> b.getId() == testBookId);
        assertTrue("Search results should contain the test book", found);
        System.out.println("Search completed. Test book found: " + testBookId);
    }

    @Test
    public void test5_UpdateBook() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            assertFalse("No test book found. Run test1_AddBook first or add a setup book.", books.isEmpty());
            testBookId = books.get(0).getId();
        }

        Book book = bookService.getBookById(testBookId);
        assertNotNull(book);

        book.setPrice(75.0);
        bookService.updateBook(book);

        Book updatedBook = bookService.getBookById(testBookId);
        assertEquals(75.0, updatedBook.getPrice(), 0.001);
        System.out.println("Book updated successfully: " + testBookId);
    }

    @Test
    public void test6_IsDuplicateBook() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            assertFalse("No test book found. Run test1_AddBook first or add a setup book.", books.isEmpty());
            testBookId = books.get(0).getId();
        }

        boolean isDuplicate = bookService.isDuplicateBook(TEST_TITLE, "English");
        assertTrue("Book should be detected as duplicate", isDuplicate);

        boolean notDuplicate = bookService.isDuplicateBook("Nonexistent Title", "English");
        assertFalse("Nonexistent book should not be duplicate", notDuplicate);

        System.out.println("Duplicate book check completed for test book: " + testBookId);
    }

    @Test
    public void test7_DeleteBook() throws SQLException {
        if (testBookId == 0) {
            List<Book> books = bookService.searchBooks(TEST_TITLE);
            if (books.isEmpty()) {
                System.out.println("No book to delete. Skipping delete test.");
                return;
            }
            testBookId = books.get(0).getId();
        }

        bookService.deleteBook(testBookId);
        Book book = bookService.getBookById(testBookId);
        assertNull("Book should be deleted", book);
        System.out.println("Book deleted successfully: " + testBookId);

        // Reset testBookId to avoid reuse
        testBookId = 0;
    }
}
