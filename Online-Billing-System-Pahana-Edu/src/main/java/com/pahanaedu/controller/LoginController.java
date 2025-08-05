package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        try {
            // Admin login (bypasses DB for security)
            if ("admin".equals(username) && "Admin@123".equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", "admin");
                session.setAttribute("username", "admin");
                session.setAttribute("role", "Admin");
                response.sendRedirect("Admin_Dashboard.jsp");
                return;
            }

            // Other users login (plain text comparison)
            User user = userService.getUserByUsername(username);

            if (user != null && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());
                response.sendRedirect("Staff_Dashboard.jsp");
            } else {
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }
}
