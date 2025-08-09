<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String deleted = request.getParameter("deleted");
    if ("true".equals(deleted)) {
%>
<script>
    alert("User deleted successfully");
</script>
<%
    }

    String updated = request.getParameter("updated");
    if ("true".equals(updated)) {
%>
<script>
    alert("User details updated successfully");
</script>
<%
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>All Users</title>
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

        <div class="container">
            <h2>List of Users in the Bookshop</h2>
            <div class="table-responsive">
                <table>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<User> users = (List<User>) request.getAttribute("userList");
                            if (users != null && !users.isEmpty()) {
                                int count = 1;
                                for (User u : users) {
                        %>
                        <tr style="text-align: center;">
                            <td><%= count%></td>
                            <td><%= u.getUsername()%></td>
                            <td><%= u.getEmail()%></td>
                            <td><%= u.getRole()%></td>
                            <td>
                                <a href="user?action=edit&id=<%= u.getId()%>" class="btn-edit">Edit</a>
                                <a href="user?action=delete&id=<%= u.getId()%>" class="btn-delete"
                                   onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                            </td>
                        </tr>
                        <%
                                count++;
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="5" style="text-align: center;">No users found.</td>
                        </tr>
                        <% }%>
                    </tbody>
                </table>
            </div>

            <div style="text-align: center; margin-top: 20px;">
                <a href="Admin_Dashboard.jsp"><button class="blue-button">Back to Admin Dashboard</button></a>
            </div>
        </div>

        <div>
            <%@ include file="footer.jsp" %>
        </div>

    </body>
</html>
