package com.schoolmanagement.schoolmanangemenet.repository;

import com.schoolmanagement.schoolmanangemenet.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface ITeacherRepository extends CrudRepository<Teacher, String> {
}
