<!DOCTYPE html>
<html>
<head>
    <title>Login - Pahana Edu Billing System</title>
    <style>
        body { font-family: Times New Roman; margin: 1.5in 1in; }
        h1 { font-size: 14pt; font-weight: bold; }
        p { font-size: 12pt; }
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Login</h1>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="login" method="post">
        <p>Username: <input type="text" name="username" required></p>
        <p>Password: <input type="password" name="password" required></p>
        <p><input type="submit" value="Login"></p>
    </form>
</body>
</html>