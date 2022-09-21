package com.schoolmanagement.repository;

import com.schoolmanagement.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ILessonRepository extends CrudRepository<Lesson, Long> {
    Optional<Lesson> findByName(String lessonName);
}
