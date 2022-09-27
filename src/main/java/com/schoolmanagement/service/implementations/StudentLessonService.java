package com.schoolmanagement.service.implementations;

import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.StudentLesson;
import com.schoolmanagement.repository.IStudentLessonRepository;
import com.schoolmanagement.service.interfaces.IStudentLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentLessonService implements IStudentLessonService {
    private IStudentLessonRepository studentLessonRepository;

    @Autowired
    public StudentLessonService (IStudentLessonRepository studentLessonRepository) {
        this.studentLessonRepository = studentLessonRepository;
    }

    @Override
    public Boolean enrollLesson (Student student, Lesson lesson) {
        StudentLesson studentLesson = new StudentLesson();
        studentLesson.setLesson(lesson);
        studentLesson.setStudent(student);
        this.studentLessonRepository.save(studentLesson);
        return true;
    }
}
