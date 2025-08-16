<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Book Information</title>
        <link rel="stylesheet" href="css/Admin_Dashboard_Style.css" />
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" href="css/Style.css" />
    </head>
    <body>
        <div class="panel-container">
            <div class="panel-title">Manage Book Information</div>

            <div class="button-container">
                <a href="BookController?action=add" class="dashboard-btn add-customer">Add Book</a>
                <a href="BookController?action=list" class="dashboard-btn add-customer">View All Books</a>
            </div>
        </div>
        <div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
