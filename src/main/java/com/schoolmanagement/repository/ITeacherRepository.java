package com.schoolmanagement.repository;

import com.schoolmanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, String> {
}
