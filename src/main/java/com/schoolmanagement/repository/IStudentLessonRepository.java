package com.schoolmanagement.repository;

import com.schoolmanagement.model.StudentLesson;
import org.springframework.data.repository.CrudRepository;

public interface IStudentLessonRepository extends CrudRepository<StudentLesson, Integer> {

}
