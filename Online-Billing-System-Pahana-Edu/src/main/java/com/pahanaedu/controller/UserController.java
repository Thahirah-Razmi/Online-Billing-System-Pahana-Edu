package com.pahanaedu.controller;

import com.pahanaedu.model.User;
import com.pahanaedu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

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

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("userList", users);
        request.getRequestDispatcher("viewUsers.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addUser.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.getUserById(id);
        request.setAttribute("user", existingUser);
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirm = request.getParameter("confirm");
        String role = request.getParameter("role");

        if (!password.equals(confirm)) {
            request.setAttribute("error", "Passwords do not match.");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        if (userService.isUsernameOrEmailExists(username, email)) {
            request.setAttribute("error", "Username or Email already exists.");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("role", role);
            request.getRequestDispatcher("addUser.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);

        userService.addUser(user);
        HttpSession session = request.getSession();
        session.setAttribute("message", "User registered successfully!");
        response.sendRedirect("user?action=add");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User existingUser = userService.getUserById(id);

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

        User updatedUser = new User(id, username, email, finalPassword, role);
        userService.updateUser(updatedUser);
        response.sendRedirect("user?action=list&updated=true");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.deleteUser(id);
        response.sendRedirect("user?action=list&deleted=true");
    }
}
