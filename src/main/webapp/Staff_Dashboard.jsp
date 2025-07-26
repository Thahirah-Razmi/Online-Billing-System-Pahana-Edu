<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Dashboard</title>
        <link rel="stylesheet" href="css/Staff_Dashboard_Style.css">
        <link rel="stylesheet" href="css/Style.css" />
    </head>
    <body>

        <div class="main-container">
            <div class="title-box">
                <h1>Staff Dashboard</h1>
            </div>

            <div class="button-container">
                <a href="manageCustomerInformation.jsp" class="dashboard-btn add-customer">Manage Customer Information</a>
                <a href="manageBookInformation.jsp" class="dashboard-btn add-book">Manage Book Information</a>
                
                <div class="center-align full-width">
                    <a href="manageBillInformation.jsp" class="dashboard-btn add-bill">Manage Bill Information</a>
                </div>
            </div>

        </div>

        <div>
            <%@ include file ="footer.jsp"%>
        </div>

    </body>
</html>

