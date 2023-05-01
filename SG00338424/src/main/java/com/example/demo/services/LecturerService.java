package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.Lecturer;
import com.example.demo.Repositories.LecturerRepo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Service
public class LecturerService {

    @Autowired
    private LecturerRepo lr;

    public Iterable<Lecturer> getLecturers() {
        return lr.findAll();
    }

    public Lecturer addLecturer(@Valid Lecturer lecturer) {
        if (lecturer.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is not allowed!");
        }

        if (lidExists(lecturer.getLid())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "LID Already Exists");
        }

        return lr.save(lecturer);
    }

    public boolean lidExists(@NotBlank String lid) {
        Optional<Lecturer> existingLecturer = lr.findByLid(lid);
        return existingLecturer.isPresent();
    }

    public Lecturer saveLecturer(Lecturer lecturer) {
        try {
            return lr.save(lecturer);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403),
                    "Lecturer: " + lecturer.getLid() + " already exists");
        }

    }

    public Lecturer updateLecturer(String lid, Lecturer lecturerUpdates) {
        if (lecturerUpdates.getName() == null || lecturerUpdates.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name cannot be left blank");
        }

        Optional<Lecturer> existingLecturer = lr.findByLid(lid);

        if (!existingLecturer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lecturer not found");
        }

        Lecturer updatedLecturer = existingLecturer.get();

        // Do not update ID or LID
        if (lecturerUpdates.getName() != null) {
            updatedLecturer.setName(lecturerUpdates.getName());
        }
        if (lecturerUpdates.getTaxBand() != null) {
            updatedLecturer.setTaxBand(lecturerUpdates.getTaxBand());
        }
        if (lecturerUpdates.getSalaryScale() != null) {
            updatedLecturer.setSalaryScale(lecturerUpdates.getSalaryScale());
        }

        return lr.save(updatedLecturer);
    }

    public Optional<Lecturer> getLecturerByLID(String lid) {
        return lr.findByLid(lid);
    }

    public Iterable<Lecturer> findLecturersByTaxBandAndSalaryScale(String taxBand, Integer salaryScale) {
        return lr.findLecturersByTaxBandAndSalaryScale(taxBand, salaryScale);
    }
}