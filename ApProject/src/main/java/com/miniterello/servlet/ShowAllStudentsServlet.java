package com.example.student.servlet;

import com.example.student.dao.StudentDAO;
import com.example.student.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/show_all")
public class ShowAllStudentsServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch all students
            List<Student> students = studentDAO.selectAllStudents();

            // Add to request
            request.setAttribute("students", students);

        } catch (Exception e) {
            // Handle unexpected errors gracefully
            e.printStackTrace();
            request.setAttribute("errorMessage", "Unable to fetch students.");
        }

        // Forward to JSP
        request.getRequestDispatcher("show.jsp").forward(request, response);
    }
}
