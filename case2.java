package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Case Study 2: Student Registration System
 * 
 * Problem Statement:
 * A college wants to develop a Spring Boot application to register students.
 * The application should accept student details and display all registered students.
 * 
 * Concepts: REST API, Controller, GET, POST, ArrayList
 * 
 * Requirements:
 * 1. Accept student name, roll number, and course.
 * 2. Save student details temporarily (using ArrayList).
 * 3. Display all registered students.
 */

@SpringBootApplication
public class case2 {

    public static void main(String[] args) {
        SpringApplication.run(case2.class, args);
        System.out.println("Student Registration System is running on http://localhost:8080");
    }
}

// ---------------- MODEL CLASS ----------------
class Student {
    private String name;
    private int rollNumber;
    private String course;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(String name, int rollNumber, String course) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.course = course;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", rollNumber=" + rollNumber + ", course=" + course + "]";
    }
}

// ---------------- REST CONTROLLER ----------------
@RestController
@RequestMapping("/students")
class StudentController {

    // Storing student records temporarily in an ArrayList
    private List<Student> studentList = new ArrayList<>();

    // Endpoint 1: Register a student (POST)
    // URL: POST http://localhost:8080/students/register (also supports POST http://localhost:8080/students)
    /*
     * Sample JSON Body:
     * {
     *     "name": "Rohan Verma",
     *     "rollNumber": 101,
     *     "course": "B.Tech Computer Science"
     * }
     */
    @PostMapping("/register")
    public String registerStudent(@RequestBody Student student) {
        studentList.add(student);
        return "Student " + student.getName() + " with Roll No " + student.getRollNumber() + " registered successfully!";
    }

    @PostMapping
    public String addStudent(@RequestBody Student student) {
        studentList.add(student);
        return "Student registered successfully!";
    }

    // Endpoint 2: Display all registered students (GET)
    // URL: GET http://localhost:8080/students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentList;
    }

    // Endpoint 3: Find a registered student by roll number (GET)
    // URL: GET http://localhost:8080/students/{rollNumber}
    @GetMapping("/{rollNumber}")
    public Student getStudentByRollNumber(@PathVariable int rollNumber) {
        for (Student s : studentList) {
            if (s.getRollNumber() == rollNumber) {
                return s;
            }
        }
        return null; // Return null or 404 if student not found
    }
}

