package com.pahanaedu.controller;// Controller Layer - Part of MVC Architecture

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanaedu.service.BookService; // Service Layer - Part of Layered Architecture
import com.pahanaedu.model.Book;  // Model Layer

@WebServlet("/allBooks") // Model-View-Controller (MVC) Architecture
public class AllBooksController extends HttpServlet {

    private static final long serialVersionUID = 1L;

     //Encapsulation
    private BookService bookService;

    public void init() {
         //Abstraction
        bookService = BookService.getInstance(); // Singleton Pattern

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // Model-View-Controller (MVC) Architecture + Abstraction
        String query = request.getParameter("query");
        List<Book> books;

        try {
            if (query != null && !query.trim().isEmpty()) {
                books = bookService.searchBooks(query); //Abstraction + Encapsulation
            } else {
                books = bookService.getAllBooks(); //Abstraction + Encapsulation
            }

            request.setAttribute("bookList", books);
            request.getRequestDispatcher("viewBooks.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error fetching books.");
            request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
