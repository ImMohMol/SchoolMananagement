package com.schoolmanagement.repository;

import com.schoolmanagement.model.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface ITeacherRepository extends CrudRepository<Teacher, String> {
}
