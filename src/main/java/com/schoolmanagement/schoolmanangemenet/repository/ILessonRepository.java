package com.schoolmanagement.schoolmanangemenet.repository;

import com.schoolmanagement.schoolmanangemenet.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ILessonRepository extends CrudRepository<Lesson, Long> {
    Optional<Lesson> findByName(String lessonName);
}
