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
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> students = studentDAO.selectAllStudents();
        request.setAttribute("students", students);
        request.getRequestDispatcher("show.jsp").forward(request, response);
    }
}
