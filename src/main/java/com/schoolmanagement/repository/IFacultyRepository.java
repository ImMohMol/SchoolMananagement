package com.schoolmanagement.repository;

import com.schoolmanagement.model.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IFacultyRepository extends CrudRepository<Faculty, Integer> {
    Optional<Faculty> findByName(String name);
}
