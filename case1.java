package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Case Study 1: Student Feedback Management System
 * 
 * Problem Statement:
 * A college wants to develop a small Spring Boot application where students can
 * submit feedback about their classes. The application should store the feedback
 * and allow the faculty to view all submitted feedback.
 * 
 * Requirements:
 * 1. Students submit their name, subject, and feedback.
 * 2. The application accepts feedback through a REST API.
 * 3. Faculty can retrieve all submitted feedback.
 * 4. Initially, data will be stored in an ArrayList instead of a database.
 */

@SpringBootApplication
public class case1 {

    public static void main(String[] args) {
        SpringApplication.run(case1.class, args);
        System.out.println("Student Feedback Management System is running on http://localhost:8080");
    }
}

// ---------------- MODEL CLASS ----------------
class Feedback {
    private String name;
    private String subject;
    private String feedback;

    // Default constructor
    public Feedback() {
    }

    // Parameterized constructor
    public Feedback(String name, String subject, String feedback) {
        this.name = name;
        this.subject = subject;
        this.feedback = feedback;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Feedback [name=" + name + ", subject=" + subject + ", feedback=" + feedback + "]";
    }
}

// ---------------- REST CONTROLLER ----------------
@RestController
@RequestMapping("/feedback")
class FeedbackController {

    // In-memory list to store feedback records
    private List<Feedback> feedbackList = new ArrayList<>();

    // Endpoint 1: Student submits feedback (POST)
    // URL: POST http://localhost:8080/feedback
    /*
     * Sample JSON Body:
     * {
     *     "name": "Aman Sharma",
     *     "subject": "Java Programming",
     *     "feedback": "Great lecture on Spring Boot and REST APIs!"
     * }
     */
    @PostMapping
    public String submitFeedback(@RequestBody Feedback feedback) {
        feedbackList.add(feedback);
        return "Feedback submitted successfully for subject: " + feedback.getSubject();
    }

    // Endpoint 2: Faculty retrieves all submitted feedback (GET)
    // URL: GET http://localhost:8080/feedback
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackList;
    }

    // Endpoint 3: Faculty can filter feedback by subject (GET)
    // URL: GET http://localhost:8080/feedback/subject/{subjectName}
    @GetMapping("/subject/{subjectName}")
    public List<Feedback> getFeedbackBySubject(@PathVariable String subjectName) {
        List<Feedback> filteredList = new ArrayList<>();
        for (Feedback f : feedbackList) {
            if (f.getSubject().equalsIgnoreCase(subjectName)) {
                filteredList.add(f);
            }
        }
        return filteredList;
    }
}

