<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Pahana Edu Billing System</title>
    <style>
        body { font-family: Times New Roman; margin: 1.5in 1in; }
        h1 { font-size: 14pt; font-weight: bold; }
        p, a { font-size: 12pt; }
    </style>
</head>
<body>
    <h1>Welcome, <%= session.getAttribute("username") %></h1>
    <p><a href="addCustomer.jsp">Add Customer</a></p>
    <p><a href="editCustomer.jsp">Edit Customer</a></p>
    <p><a href="manageItems.jsp">Manage Items</a></p>
    <p><a href="calculateBill.jsp">Calculate and Print Bill</a></p>
    <p><a href="viewDetails.jsp">View Account Details</a></p>
    <p><a href="help.jsp">Help</a></p>
    <p><a href="logout">Logout</a></p>
</body>
</html>