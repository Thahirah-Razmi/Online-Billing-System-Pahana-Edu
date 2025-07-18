<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null || !"admin".equalsIgnoreCase(admin)) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>User Registration</title>
        <link rel="stylesheet" href="css/Admin_Dashboard_Style.css" />
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" type="text/css" href="css/Header_Style.css">
        <link rel="stylesheet" href="css/Style.css" />
    </head>
    <body>
        <header class="app-header">
            <nav class="custom-navbar">
                <div class="navbar-container">
                    <div class="navbar">
                        <img src="Images/logo.png" alt="Logo" class="logo">
                        <span>Pahana Edu Bookshop</span>
                        <ul class="nav-links">
                            <li class="dropdown">
                                <span class="dropbtn">Manage User Information</span>
                                <div class="dropdown-content">
                                    <a href="addUser.jsp">Add User</a>
                                    <a href="user?action=list">View All Users</a>
                                </div>
                            </li>
                            <li class="dropdown">
                                <span class="dropbtn">Manage Customer Information</span>
                                <div class="dropdown-content">
                                    <a href="addCustomer.jsp">Add Customer</a>
                                    <a href="CustomerController?action=list">View All Customers</a>
                                </div>
                            </li>
                            <li class="dropdown">
                                <span class="dropbtn">Manage Item Information</span>
                                <div class="dropdown-content">
                                    <a href="addBook.jsp">Add Books</a>
                                    <a href="allBooks">View All Books</a>
                                </div>
                            </li>
                            <li class="dropdown">
                                <span class="dropbtn">Manage Bill</span>
                                <div class="dropdown-content">
                                    <a href="BillController?action=add">Add New Bill</a>
                                    <a href="BillController?action=list">View Sales</a>
                                </div>
                            </li>              

                        </ul>
                        <ul class="nav-right">
                            <li><a class="logout-link" href="logout">	 Logout</a></li>
                        </ul>
                        <ul class="nav-right">
                            <li><a class="help-link" href="help.jsp">&nbsp Help &nbsp </a></li>
                        </ul>
                    </div>
                </div>
                </div>
            </nav>
        </header>

        <div class="container-form">
            <h2>Staff User Registration</h2>

            <%
                String message = (String) session.getAttribute("message");
                if (message != null) {
            %>
            <div class="success-message"><%= message%></div>
            <%
                    session.removeAttribute("message");
                }

                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <div class="error-message"><%= error%></div>
            <%
                }
            %>

            <form action="user?action=add" method="post">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : ""%>" required />
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="email" id="email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : ""%>" required />
                </div>

                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" required
                           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                           title="Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character." />
                </div>

                <div class="form-group">
                    <label for="confirm">Confirm Password</label>
                    <input type="password" id="confirm" name="confirm" required />
                </div>

                <div class="form-group">
                    <label for="role">User Role</label>
                    <select id="role" name="role" required>
                        <option value="" disabled selected>Select role</option>
                        <option value="Cashier" <%= "Cashier".equals(request.getAttribute("role")) ? "selected" : ""%>>Cashier</option>
                        <option value="Manager" <%= "Manager".equals(request.getAttribute("role")) ? "selected" : ""%>>Manager</option>
                    </select>
                </div>

                <button type="submit">Register</button>
            </form>

            <div style="margin-top: 20px; text-align: center;">
                <a href="Admin_Dashboard.jsp">
                    <button type="button" class="blue-button">Back to Admin Dashboard</button>
                </a>
            </div>
        </div>

        <div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
