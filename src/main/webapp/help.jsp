<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Help Section</title>
        <link rel="stylesheet" type="text/css" href="css/Header_Style.css">
        <style>
            body {
                background-color: #F5ECD9;
                font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
                margin: 0;
                padding: 0;
            }
            .container-help {
                width: 70%;
                max-width: 800px;
                margin: 100px auto;
                padding: 30px;
                background-color: white;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0,0,0,0.1);
            }
            h1 {
                text-align: center;
                color: #F5ECD9;
                background-color: #4B2E1E;
                padding: 15px 20px;
                border-radius: 8px;
                margin-bottom: 30px;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            }
            .help-item {
                margin-bottom: 25px;
                padding-bottom: 15px;
                border-bottom: 1px dashed #C2B280;
            }
            .help-item h3 {
                color: #4B2E1E;
                margin-bottom: 10px;
            }
            .help-item p {
                color: #4B2E1E;
                line-height: 1.6;
                margin: 8px 0;
            }
            .back-button {
                display: block;
                width: 150px;
                margin: 30px auto 0;
                padding: 10px;
                background-color: #708238;
                color: white;
                text-align: center;
                text-decoration: none;
                border-radius: 5px;
                font-size: 16px;
            }
            .back-button:hover {
                background-color: #566b2f;
            }

            .footer {
                text-align: center;
                padding: 2px 0px;
                background-color: #4B2E1E;
                font-size: 14px;
                color: #F5ECD9;
                margin-top: 100px;
                line-height: 1;
            }

            .footer-line {
                border: none;
                height: 1px;
                background-color: #C2B280;
                margin-bottom: 8px;
            }

            .footer a {
                color: #D36B43;
                text-decoration: none;
                margin: 0 5px;
            }

            .footer a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container-help">
            <h1>Pahana Edu Billing System - Help</h1>

            <div class="help-item">
                <h3>1. How to Login <span style="font-size: 14px; color: #708238;">(Admin & Staff)</span> </h3>
                <p>Enter your username and password on the login page. Contact admin if you forget credentials.</p>
            </div>

            <div class="help-item">
                <h3>2. Managing User Information <span style="font-size: 14px; color: #D36B43;">(Admin Only)</span> </h3>
                <p><strong>Add Customer:</strong> Go to Dashboard > Managing User Information > Add User.</p>
                <p><strong>Edit Customer:</strong> Go to Dashboard > Managing User Information > View All Users > Edit.</p>
                <p><strong>Delete Customer:</strong> Go to Dashboard > Managing User Information > View All Users > Delete.</p>
            </div>

            <div class="help-item">
                <h3>3. Managing Customer Information <span style="font-size: 14px; color: #708238;">(Admin & Staff)</span> </h3>
                <p><strong>Add Customer:</strong> Go to Dashboard > Managing Customer Information > Add Customer.</p>
                <p><strong>View All Customers:</strong> Go to Dashboard > Managing Customer Information > View All Customers > Search for a customer.</p>
                <p><strong>Edit Customer:</strong> Go to Dashboard > Managing Customer Information > View All Customers > Search for a customer > Edit.</p>
                <p><strong>Delete Customer:</strong> Go to Dashboard > Managing Customer Information > View All Customers > Search for a customer > Delete.</p>
            </div>

            <div class="help-item">
                <h3>4. Managing Book Information <span style="font-size: 14px; color: #708238;">(Admin & Staff)</span> </h3>
                <p><strong>Add Book:</strong> Go to Dashboard > Managing Book Information > Add Book.</p>
                <p><strong>View All Books:</strong> Go to Dashboard > Managing Book Information > View All Books > Search for a book.</p>
                <p><strong>Edit Customer:</strong> Go to Dashboard > Managing Book Information > View All Books > Search for a book > Edit.</p>
                <p><strong>Delete Customer:</strong> Go to Dashboard > Managing Book Information > View All Books > Search for a book > Delete.</p>
            </div>

            <div class="help-item">
                <h3>5. Manage Bill Information <span style="font-size: 14px; color: #708238;">(Admin & Staff)</span> </h3>
                <p><strong>Calculate Bill:</strong> Go to Dashboard > Managing Bill Information > Calculate Bill.</p>
                <p><strong>View All Sales:</strong> Go to Dashboard > Managing Bill Information > View All Sales > Search for a bill by filtering.</p>
                <p><strong>Delete Bill:</strong> Go to Dashboard > Managing Bill Information > View All Sales > Search for a bill > Delete.</p>
            </div>

            <div class="help-item">
                <h3>6. Troubleshooting <span style="font-size: 14px; color: #708238;">(Admin & Staff)</span> </h3>
                <p><strong>Error messages:</strong> Ensure all fields are filled correctly.</p>
                <p><strong>Contact Support:</strong> Email: support@pahanaedu.lk</p>
            </div>
        </div>
        <div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
