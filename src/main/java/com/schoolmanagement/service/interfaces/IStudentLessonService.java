package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.Student;

public interface IStudentLessonService {
    Boolean enrollLesson (Student student, Lesson lesson);
}
