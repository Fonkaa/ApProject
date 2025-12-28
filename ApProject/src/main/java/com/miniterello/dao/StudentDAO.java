package com.example.student.dao;

import com.example.student.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Database configuration
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    // SQL queries
    private static final String INSERT_STUDENT_SQL =
            "INSERT INTO students (name, email, year) VALUES (?, ?, ?)";

    private static final String SELECT_ALL_STUDENTS_SQL =
            "SELECT * FROM students";

    private static final String SELECT_STUDENT_BY_ID_SQL =
            "SELECT * FROM students WHERE id = ?";

    private static final String UPDATE_STUDENT_SQL =
            "UPDATE students SET name = ?, email = ?, year = ? WHERE id = ?";

    private static final String DELETE_STUDENT_SQL =
            "DELETE FROM students WHERE id = ?";

    public StudentDAO() {
    }

    // Get database connection (same logic, cleaner)
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL Driver not found", e);
        }
        return DriverManager.getConnection(
                JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    // Insert new student
    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_STUDENT_SQL)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getYear());

            preparedStatement.executeUpdate();
        }
    }

    // Select all students
    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_STUDENTS_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("year")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // Select student by ID
    public Student selectStudentById(int id) {
        Student student = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_STUDENT_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    student = new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getInt("year")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    // Update student
    public boolean updateStudent(Student student) throws SQLException {
        boolean updated;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_STUDENT_SQL)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getYear());
            preparedStatement.setInt(4, student.getId());

            updated = preparedStatement.executeUpdate() > 0;
        }
        return updated;
    }

    // Delete student
    public boolean deleteStudent(int id) throws SQLException {
        boolean deleted;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_STUDENT_SQL)) {

            preparedStatement.setInt(1, id);
            deleted = preparedStatement.executeUpdate() > 0;
        }
        return deleted;
    }
}
