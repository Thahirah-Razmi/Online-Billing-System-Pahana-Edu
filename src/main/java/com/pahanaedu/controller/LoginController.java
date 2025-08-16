package com.pahanaedu.controller;

import com.pahanaedu.model.User; // Layered Architecture: Model Layer
import com.pahanaedu.service.UserService; // Layered Architecture: Service Layer

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Encapsulation
    private UserService userService;

    // Singleton Pattern + Abstractio
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          // MVC Pattern
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Abstraction
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        try {
            if ("admin".equals(username) && "Admin@123".equals(password)) {
                
                 // Encapsulation
                HttpSession session = request.getSession();
                session.setAttribute("admin", "admin");
                session.setAttribute("username", "admin");  // Encapsulation
                session.setAttribute("role", "Admin");
                response.sendRedirect("Admin_Dashboard.jsp"); // MVC
                return;
            }

            User user = userService.getUserByUsername(username); // Abstraction

            // Encapsulation
            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user); // Encapsulation
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());
                response.sendRedirect("Staff_Dashboard.jsp");  // MVC
            } else {
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
            // Layered Architecture
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }
}
