<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.student.model.Student" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>All Students</title>
</head>
<body>
<h2>All Students</h2>

<% 
    List<Student> students = (List<Student>) request.getAttribute("students");
    if (students == null || students.isEmpty()) { 
%>
    <p>No students found.</p>
<% } else { %>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Year</th>
        </tr>
        <% for (Student s : students) { %>
            <tr>
                <td><%= s.getId() %></td>
                <td><%= s.getName() %></td>
                <td><%= s.getEmail() %></td>
                <td><%= s.getYear() %></td>
            </tr>
        <% } %>
    </table>
<% } %>

<br>
<a href="index.jsp">Add New Student</a>
</body>
</html>
