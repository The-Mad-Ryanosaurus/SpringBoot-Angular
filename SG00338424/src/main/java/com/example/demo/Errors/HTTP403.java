package com.example.demo.Errors;

public class HTTP403 extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HTTP403(String message) {
        super(message);
    }
}