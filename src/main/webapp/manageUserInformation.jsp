<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage User Information</title>
        <link rel="stylesheet" href="css/Admin_Dashboard_Style.css" />
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" href="css/Style.css" />
    </head>
    <body>
        <div class="panel-container">
            <div class="panel-title">Manage Customer Information</div>

            <div class="button-container">
                <a href="addUser.jsp" class="dashboard-btn add-user">Add User</a>
                <a href="user?action=list" class="dashboard-btn view-user">View All Users</a>
            </div>
    </body>
</html>
