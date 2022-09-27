package com.schoolmanagement.repository;

import com.schoolmanagement.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IFacultyRepository extends JpaRepository<Faculty, Long> {
    Optional<Faculty> findByName(String name);
}
