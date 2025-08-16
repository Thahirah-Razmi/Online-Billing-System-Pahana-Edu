package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user") // MVC Pattern
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Encapsulation
    private UserService userService;

    // Singleton Pattern + Abstraction
    public void init() throws ServletException {
        userService = UserService.getInstance();  // Singleton Pattern
    }

    // MVC Pattern
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null || action.equals("list")) {
                listUsers(request, response);
            } else if (action.equals("add")) {
                showAddForm(request, response);
            } else if (action.equals("edit")) {
                showEditForm(request, response);
            } else if (action.equals("delete")) {
                deleteUser(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action.equals("add")) {
                addUser(request, response);
            } else if (action.equals("update")) {
                updateUser(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }

    // Abstraction + MVC Pattern
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<User> users = userService.getAllUsers(); // Abstraction
        request.setAttribute("userList", users);
        request.getRequestDispatcher("viewUsers.jsp").forward(request, response); // MVC Pattern
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addUser.jsp").forward(request, response);
    }

    // Layered Architecture + Encapsulation
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.getUserById(id); // Encapsulation
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    // Abstraction + Encapsulation
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String role = request.getParameter("role");

        request.setAttribute("username", username);
        request.setAttribute("email", email);
        request.setAttribute("role", role);

        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(emailRegex)) {
            request.setAttribute("error", "Invalid email format.");
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Passwords Don't Match.");
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        if (userService.isUsernameOrEmailExists(username, email)) {
            request.setAttribute("error", "Username or Email Already Exists.");
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        // Abstraction + Encapsulation
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password); // Encapsulation
        user.setRole(role);

        // Service Layer Pattern
        userService.addUser(user);  // Abstraction
        HttpSession session = request.getSession();
        session.setAttribute("message", "User registered successfully!");
        response.sendRedirect("user?action=add");
    }

    // Layered Architecture
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User existingUser = userService.getUserById(id);// Abstraction

        if (existingUser == null) {
            request.setAttribute("errorMessage", "User not found.");
            try {
                request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }

        String finalPassword = (password == null || password.trim().isEmpty())
                ? existingUser.getPassword()
                : password;

        // Encapsulation
        User updatedUser = new User(id, username, email, finalPassword, role);
        userService.updateUser(updatedUser); // Abstraction
        response.sendRedirect("user?action=list&updated=true");
    }

    // Abstraction
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id); // Abstraction
        response.sendRedirect("user?action=list&deleted=true");
    }
}
