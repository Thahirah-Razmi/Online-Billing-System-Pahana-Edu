package com.pahanaedu.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")  // MVC Pattern 
public class LogoutController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Abstraction + Encapsulation:
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Encapsulation
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // MVC Pattern:
        response.sendRedirect("Login.jsp");
    }
}
