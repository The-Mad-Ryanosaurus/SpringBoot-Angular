package com.example.demo.controllers;

import com.example.demo.Errors.ErrorResponseBuilder;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentController {

    // Inject the StudentService object into the controller using @Autowired
    // annotation
    @Autowired
    private StudentService studentService;

    // HTTP GET request to retrieve all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // HTTP GET request to retrieve a student by their SID
    @GetMapping("/{sid}")
    public ResponseEntity<Student> getStudentBySid(@PathVariable String sid) {
        // Call the getStudentBySid() method of the StudentService to get the student by
        // SID
        Optional<Student> optionalStudent = studentService.getStudentBySid(sid);

        // If the student exists, return the student in the response body with HTTP
        // status code 200 OK
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(optionalStudent.get());
        } else { // Otherwise, return HTTP status code 404 NOT FOUND
            return ResponseEntity.notFound().build();
        }
    }

    // HTTP DELETE request to delete a student by their SID
    @DeleteMapping("/{sid}")
    public ResponseEntity<ErrorResponseBuilder> deleteStudentBySid(@PathVariable String sid) {
        // Call the deleteStudentBySid() method of the StudentService to delete the
        // student by SID
        return studentService.deleteStudentBySid(sid);
    }
}