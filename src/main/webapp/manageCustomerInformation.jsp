<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Customer Information</title>
        <link rel="stylesheet" href="css/Admin_Dashboard_Style.css" />
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" href="css/Style.css" />
    </head>
    <body>
        <div class="panel-container">
            <div class="panel-title">Manage Customer Information</div>

            <div class="button-container">
                <a href="CustomerController?action=add" class="dashboard-btn add-customer">Add Customer</a>
                <a href="CustomerController?action=list" class="dashboard-btn view-customer">View All Customers</a>
            </div>
        </div>
        <div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
