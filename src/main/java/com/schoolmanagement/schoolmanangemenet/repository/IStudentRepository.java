package com.schoolmanagement.schoolmanangemenet.repository;

import com.schoolmanagement.schoolmanangemenet.model.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IStudentRepository extends CrudRepository<Student, String> {
    Optional<Student> findByNationalCode(String nationalCode);
}
