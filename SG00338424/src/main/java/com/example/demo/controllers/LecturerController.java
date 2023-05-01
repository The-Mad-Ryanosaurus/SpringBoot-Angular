package com.example.demo.controllers;

import com.example.demo.Errors.ErrorResponseBuilder;
import com.example.demo.Errors.HTTP403;
import com.example.demo.Errors.HTTP500;
import com.example.demo.Validation.LecturerPOSTValidation;
import com.example.demo.Validation.LecturerPUTValidation;
import com.example.demo.models.Lecturer;
import com.example.demo.services.LecturerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/lecturers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LecturerController {

    @Autowired
    LecturerService ls;

    @GetMapping
    public ResponseEntity<Iterable<Lecturer>> getLecturers() {
        return ResponseEntity.ok(ls.getLecturers());
    }

    @GetMapping("/{lid}")
    public ResponseEntity<Lecturer> getLecturerByLid(@PathVariable String lid) {
        return ls.getLecturerByLID(lid)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Lecturer>> searchLecturers(
            @RequestHeader(value = "Tax-Band", required = false) String taxBand,
            @RequestHeader(value = "Salary-Scale", required = false) Integer salaryScale) {
        return ResponseEntity.ok(ls.findLecturersByTaxBandAndSalaryScale(taxBand, salaryScale));
    }

    @PostMapping
    @Validated(LecturerPOSTValidation.class)
    public ResponseEntity<Lecturer> addLecturer(
            @Valid @RequestBody Lecturer lecturerValidation) {

        Lecturer createdLecturer = ls.saveLecturer(lecturerValidation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLecturer);
    }

    @PutMapping(path = "/{lid}")
    @Validated(LecturerPUTValidation.class)
    public ResponseEntity<Lecturer> updateLecturer(@PathVariable("lid") String lid,
            @Valid @RequestBody Lecturer updatedLecturer) {
        return ResponseEntity.ok(ls.updateLecturer(lid, updatedLecturer));
    }
}