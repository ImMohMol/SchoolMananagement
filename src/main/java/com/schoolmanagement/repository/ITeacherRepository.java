package com.schoolmanagement.repository;

import com.schoolmanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByPersonalNo(String personalNo);
}
