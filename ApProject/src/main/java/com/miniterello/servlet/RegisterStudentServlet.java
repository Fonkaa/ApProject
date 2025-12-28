package com.example.student.servlet;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterStudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String yearStr = request.getParameter("year");

        String errorMessage = null;

        // Simple validation
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || yearStr == null || yearStr.isEmpty()) {
            errorMessage = "All fields are required!";
        }

        int year = 0;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            errorMessage = "Year must be a number!";
        }

        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Student newStudent = new Student(name, email, year);

        try {
            studentDAO.insertStudent(newStudent);
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage = "Database error: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("show_all");
    }
}
