package com.schoolmanagement.schoolmanangemenet.repository;

import com.schoolmanagement.schoolmanangemenet.model.Lesson;
import org.springframework.data.repository.CrudRepository;

public interface ILessonRepository extends CrudRepository<Lesson, Integer> {
}
