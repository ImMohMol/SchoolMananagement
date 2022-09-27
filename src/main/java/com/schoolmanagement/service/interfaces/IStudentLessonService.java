package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.StudentLesson;

import java.util.List;

public interface IStudentLessonService {
    List<StudentLesson> findStudentLessons (Student student);

    Boolean enrollLesson (Student student, Lesson lesson);
}
