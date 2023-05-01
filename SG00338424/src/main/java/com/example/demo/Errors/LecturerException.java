package com.example.demo.Errors;

// Custom exception class for handling Lecturer errors.
public class LecturerException extends RuntimeException {
    public LecturerException(String message) {
        super(message);
    }
}