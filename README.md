Employee Database App (Java + JDBC + MySQL)
This is a simple Java console application that performs CRUD operations (Create, Read, Update, Delete) on an employee database using JDBC and MySQL.

Features
Add a new employee

View all employees

Update employee details

Delete employee by ID

JDBC-based connection to MySQL

Tech Stack
Java

JDBC

MySQL

MySQL Connector/J (JDBC driver)

VS Code / Command Line

Setup Instructions
Create the Database:

Open MySQL command line and run:

CREATE DATABASE employee_db;
USE employee_db;

CREATE TABLE employee (
id INT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(100),
email VARCHAR(100),
salary DOUBLE
);

Project Structure

employeedbapp/
├── lib/
│ └── mysql-connector-java-8.3.0.jar (Not included in repo)
├── DBConnection.java
├── EmployeeApp.java
├── .gitignore
└── README.md

Note: lib/ and .jar files are not pushed to GitHub.

Add MySQL JDBC Driver

Download MySQL Connector/J from: https://dev.mysql.com/downloads/connector/j/

Extract the ZIP and place the .jar file inside the lib/ folder of your project.

Compile & Run

Make sure you're in the employeedbapp/ folder.

To compile (on Windows):

javac -cp ".;lib/mysql-connector-java-8.3.0.jar" *.java

To run:

java -cp ".;lib/mysql-connector-java-8.3.0.jar" EmployeeApp

Note: On macOS/Linux, replace ; with : in the classpath.

Important Notes
Do not hardcode your password in DBConnection.java when pushing to GitHub.

Replace it with a placeholder like "password_here" or use a config.properties file (excluded via .gitignore).

Sample Menu
Add Employee

View Employees

Update Employee

Delete Employee

Exit
