-- Create database
CREATE DATABASE studentdb;

-- Use database
USE studentdb;

-- Create table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    year INT NOT NULL
);

-- Optional: Insert sample data
INSERT INTO students (name, email, year) VALUES 
('John Doe', 'john@example.com', 2),
('Jane Smith', 'jane@example.com', 3);
