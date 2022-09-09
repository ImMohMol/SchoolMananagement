package com.schoolmanagement.schoolmanangemenet.repository;

import com.schoolmanagement.schoolmanangemenet.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface IStudentRepository extends CrudRepository<Student, String> {
}
