package com.example.demo.Errors;

public class HTTP500 extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HTTP500(String message) {
        super(message);
    }
}