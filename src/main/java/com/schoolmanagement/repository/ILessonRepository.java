package com.schoolmanagement.repository;

import com.schoolmanagement.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findByName(String lessonName);
}
