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
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Read parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String yearStr = request.getParameter("year");

        String errorMessage = null;
        int year = 0;

        // ================= VALIDATION =================
        if (name == null || name.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || yearStr == null || yearStr.trim().isEmpty()) {

            errorMessage = "All fields are required!";
        } else {
            try {
                year = Integer.parseInt(yearStr.trim());
            } catch (NumberFormatException e) {
                errorMessage = "Year must be a valid number!";
            }
        }

        // ================= ERROR HANDLING =================
        if (errorMessage != null) {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // ================= BUSINESS LOGIC =================
        Student newStudent = new Student(name.trim(), email.trim(), year);

        try {
            studentDAO.insertStudent(newStudent);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error occurred!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // ================= SUCCESS =================
        response.sendRedirect("show_all");
    }
}
