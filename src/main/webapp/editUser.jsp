<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="com.pahanaedu.model.User" %>
<%
    String admin = (String) session.getAttribute("admin");
    if (admin == null || !"admin".equalsIgnoreCase(admin)) {
        response.sendRedirect("Login.jsp");
        return;
    }
    User u = (User) request.getAttribute("user");
    if (u == null) {
        response.sendRedirect("viewUsers.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update User</title>
        <link rel="stylesheet" href="css/Admin_Dashboard_Style.css" />
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" type="text/css" href="css/Header_Style.css">
        <link rel="stylesheet" href="css/Style.css">
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

        <div class="container-login">
            <h2>Update User</h2>
            <form action="user?action=update" method="post">
                <input type="hidden" name="id" value="<%= u.getId()%>">

                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" value="<%= u.getUsername()%>" required>
                </div>

                <div class="form-group">
                    <label for="email">Email Address</label>
                    <input type="text" id="email" name="email" value="<%= u.getEmail()%>" required>
                </div>

                <div class="form-group">
                    <label for="role">User Role</label>
                    <select id="role" name="role" required>
                        <option value="Cashier" <%= u.getRole().equals("Cashier") ? "selected" : ""%>>Cashier</option>
                        <option value="Manager" <%= u.getRole().equals("Manager") ? "selected" : ""%>>Manager</option>
                    </select>
                </div>

                <div class="form-group">
                    <label>New Password (Leave blank to keep existing password)</label>
                    <input type="password" name="password" minlength="6">
                </div>

                <button type="submit" class="wide-button">Update</button>
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
