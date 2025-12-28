<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Student</title>
</head>
<body>
<h2>Register New Student</h2>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <p style="color:red;"><%= errorMessage %></p>
<% } %>

<form action="register" method="post">
    Name: <input type="text" name="name" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Year: <input type="number" name="year" required><br><br>
    <input type="submit" value="Register">
</form>

<br>
<a href="show_all">Show All Students</a>
</body>
</html>

