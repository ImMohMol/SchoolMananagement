package com.schoolmanagement.repository;

import com.schoolmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByNationalCode(String nationalCode);

    Optional<Student> findByStudentNo(String studentNo);
}
