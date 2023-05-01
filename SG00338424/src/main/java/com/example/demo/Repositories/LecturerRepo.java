package com.example.demo.Repositories;

import com.example.demo.models.Lecturer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepo extends CrudRepository<Lecturer, Integer> {
    Optional<Lecturer> findByLid(String lid);

    // @Query(value = "SELECT * FROM Lecturer WHERE tax_band = ?1", nativeQuery =
    // true)
    // Iterable<Lecturer> findLecturersByTaxBand(String taxBand);

    // @Query(value = "SELECT * FROM Lecturer WHERE salary_scale >= :salaryScale",
    // nativeQuery = true)
    // Iterable<Lecturer> findLecturersBySalaryScaleOrGreater(int salaryScale);

    @Query(value = "SELECT * FROM Lecturer l WHERE (:taxBand IS NULL OR l.tax_band = :taxBand) AND (:salaryScale IS NULL OR l.salary_scale >= :salaryScale)", nativeQuery = true)
    Iterable<Lecturer> findLecturersByTaxBandAndSalaryScale(@Param("taxBand") String taxBand,
            @Param("salaryScale") Integer salaryScale);
}
