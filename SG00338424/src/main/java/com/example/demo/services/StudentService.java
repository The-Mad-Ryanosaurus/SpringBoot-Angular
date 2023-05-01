package com.example.demo.services;

import com.example.demo.Errors.ErrorResponseBuilder;
import com.example.demo.Repositories.StudentRepo;
import com.example.demo.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentBySid(String sid) {
        return studentRepo.findBySid(sid);
    }

    public ResponseEntity<ErrorResponseBuilder> deleteStudentBySid(String sid) {
        Optional<Student> optionalStudent = studentRepo.findBySid(sid);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (!student.getModules().isEmpty()) {
                ErrorResponseBuilder errorResponseBuilder = ErrorResponseBuilder.buildErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR, "Cannot delete student with associated modules",
                        "/Students/" + sid);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseBuilder);
            }

            studentRepo.delete(student);

            ErrorResponseBuilder successResponseBuilder = ErrorResponseBuilder.buildErrorResponse(
                    HttpStatus.OK, "Student deleted successfully", "/Students/" + sid);
            return ResponseEntity.ok().body(successResponseBuilder);
        } else {
            ErrorResponseBuilder errorResponseBuilder = ErrorResponseBuilder.buildErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Student not found", "/Students/" + sid);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseBuilder);
        }
    }
}