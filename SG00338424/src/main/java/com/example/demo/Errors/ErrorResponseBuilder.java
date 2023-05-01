package com.example.demo.Errors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorResponseBuilder {

    private final int status;
    private final LocalDateTime timestamp;
    private String message;
    private String errors;
    private String path;

    private ErrorResponseBuilder(HttpStatus status) {
        this.status = status.value();
        this.timestamp = LocalDateTime.now();
    }

    public static ErrorResponseBuilder buildErrorResponse(HttpStatus status, String message, String path) {
        ErrorResponseBuilder builder = new ErrorResponseBuilder(status);
        builder.errors = status.getReasonPhrase();
        builder.message = message;
        builder.path = path;
        return builder;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrors() {
        return errors;
    }

    public String getPath() {
        return path;
    }
}