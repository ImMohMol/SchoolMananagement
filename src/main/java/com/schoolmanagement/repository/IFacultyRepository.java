package com.schoolmanagement.repository;

import com.schoolmanagement.model.Faculty;
import org.springframework.data.repository.CrudRepository;

public interface IFacultyRepository extends CrudRepository<Faculty, Integer> {
}
