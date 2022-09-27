package com.schoolmanagement.repository;

import com.schoolmanagement.model.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IFacultyRepository extends CrudRepository<Faculty, Long> {
    Optional<Faculty> findByName(String name);
}
