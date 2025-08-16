package com.pahanaedu.service;

import com.pahanaedu.dao.BookDAO;
import com.pahanaedu.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {

    //Encapsulation
    private static BookService instance;
    private BookDAO bookDAO;

    // Singleton Pattern
    private BookService() {
        bookDAO = new BookDAO();
    }

    // Singleton Pattern
    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    //Abstraction
    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    //Abstraction
    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    //Abstraction
    public List<Book> searchBooks(String keyword) {
        return bookDAO.searchBooks(keyword);
    }

    //Abstraction
    public Book getBookById(int id) throws SQLException {
        return bookDAO.getBookById(id);
    }

    //Abstraction
    public void updateBook(Book book) throws SQLException {
        bookDAO.updateBook(book);
    }

    //Abstraction
    public void deleteBook(int id) throws SQLException {
        bookDAO.deleteBook(id);
    }

    public boolean isDuplicateBook(String title, String language) throws SQLException {
        return bookDAO.isBookNameOrLanguageExists(title, language);
    }

}
