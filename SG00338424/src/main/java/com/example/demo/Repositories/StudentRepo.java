package com.example.demo.Repositories;

import com.example.demo.models.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
    Optional<Student> findBySid(String sid);
}