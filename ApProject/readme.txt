Student Management Web Application
==================================

Description:
-------------
This is a simple Java Servlet-based web application to manage students.
It allows you to:
- Add new students
- View all students
- Store student information in a MySQL database

Technologies Used:
------------------
- Java 8+
- JSP / Servlets
- Apache Tomcat 9
- MySQL 8
- JDBC

Project Structure:
------------------
StudentManagement/
├── src/
│   └── main/java/
│       └── com/example/student/
│           ├── dao/               --> StudentDAO.java
│           ├── model/             --> Student.java
│           └── servlet/           --> RegisterStudentServlet.java, ShowAllStudentsServlet.java
├── WebContent/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── classes/ (compiled .class files)
│   ├── index.jsp
│   └── show.jsp
├── pom.xml (optional if using Maven)
└── README.txt

Database Setup:
---------------
1. Create database:
   CREATE DATABASE studentdb;

2. Create students table:
   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       year INT NOT NULL
   );

3. Optional: Add sample data:
   INSERT INTO students (name, email, year) VALUES ('John Doe','john@example.com',2);

Deployment:
-----------
1. Make sure MySQL is running.
2. Put MySQL Connector JAR in Tomcat's lib folder (if not using Maven):
   C:\apache-tomcat-9\lib\mysql-connector-java-8.x.x.jar
3. Deploy project folder or WAR to Tomcat's webapps folder.
4. Start Tomcat:
   C:\apache-tomcat-9\bin\startup.bat
5. Open browser:
   http://localhost:8080/student-management/
6. Add students and view all students.

Notes:
------
- Servlets do not run inside VS Code directly.
- Ensure Tomcat version is compatible with javax.servlet (Tomcat 9).
- If using Maven, run 'mvn clean package' to generate WAR for deployment.

Contact:
--------
For any issues, please contact the developer.

