<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"admin".equalsIgnoreCase(role)) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Admin Dashboard</title>

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

        <div class="panel-container">
            <div class="panel-title">Admin Dashboard</div>

            <div class="button-container">
                <a href="manageUserInformation.jsp" class="dashboard-btn add-user">Manage User Information</a>
                <a href="manageCustomerInformation.jsp" class="dashboard-btn add-customer">Manage Customer Information</a>
                <a href="manageBookInformation.jsp" class="dashboard-btn add-customer">Manage Book Information</a>
                <a href="manageBillInformation.jsp" class="dashboard-btn add-user">Manage Bill Information</a>
            </div>
        </div>
        <div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
