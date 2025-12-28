package com.example.student.dao;

import com.example.student.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/studentdb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students";

    public StudentDAO() {}

    // Get database connection
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Insert new student
    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getYear());
            preparedStatement.executeUpdate();
        }
    }

    // Get all students
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int year = rs.getInt("year");
                students.add(new Student(id, name, email, year));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
