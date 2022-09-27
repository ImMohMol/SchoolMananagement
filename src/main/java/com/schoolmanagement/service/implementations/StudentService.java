package com.schoolmanagement.service.implementations;

import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.StudentLesson;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.model.dto.student.*;
import com.schoolmanagement.repository.IStudentRepository;
import com.schoolmanagement.service.interfaces.ILessonService;
import com.schoolmanagement.service.interfaces.IStudentLessonService;
import com.schoolmanagement.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    private final IStudentLessonService studentLessonService;
    private final IStudentRepository studentRepository;
    private final ILessonService lessonService;

    @Autowired
    public StudentService (IStudentRepository studentRepository, ILessonService lessonService, IStudentLessonService studentLessonService) {
        this.studentLessonService = studentLessonService;
        this.studentRepository = studentRepository;
        this.lessonService = lessonService;
    }

    @Override
    public Boolean create (CreateStudentDTO createStudentDTO) {
        Optional<Student> handler = this.studentRepository.findByNationalCode(createStudentDTO.getNationalCode());
        if (handler.isPresent()) {
            throw new ApiRequestException("This student exists in the database and cant insert it again!");
        } else {
            this.studentRepository.save(Objects.requireNonNull(GeneralMapper.convert(createStudentDTO, Student.class)));
            return true;
        }
    }

    @Override
    public List<ReadStudentsDTO> read () {
        List<ReadStudentsDTO> receivedStudents = new ArrayList<>();
        this.studentRepository.findAll().forEach((student -> receivedStudents.add(GeneralMapper.convert(student, ReadStudentsDTO.class))));
        return receivedStudents;
    }

    @Override
    public List<ReadStudentsDTO> readPaginated (int page, int size) {
        List<Student> students = this.studentRepository.findAll(PageRequest.of(page, size)).toList();
        List<ReadStudentsDTO> result = new ArrayList<>();
        students.forEach((student -> result.add(GeneralMapper.convert(student, ReadStudentsDTO.class))));
        return result;
    }

    @Override
    public Boolean update (UpdateStudentDTO updateStudentDTO) {
        Optional<Student> handler = this.studentRepository.findById(updateStudentDTO.getStudentNo());
        if (handler.isPresent()) {
            this.studentRepository.save(Objects.requireNonNull(GeneralMapper.convert(updateStudentDTO, Student.class)));
            return true;
        } else {
            throw new ApiRequestException("This student does not exist in the database!");
        }
    }

    @Override
    public Boolean delete (DeleteStudentDTO deleteStudentDTO) {
        Optional<Student> handler = this.studentRepository.findById(deleteStudentDTO.getStudentNo());
        if (handler.isPresent()) {
            this.studentRepository.delete(Objects.requireNonNull(GeneralMapper.convert(deleteStudentDTO, Student.class)));
            return true;
        } else {
            throw new ApiRequestException("This student does not exist in the database");
        }
    }

    @Override
    public Boolean enrollLesson (EnrollLessonDTO enrollLessonDTO) {
        Optional<Lesson> lessonHandler = this.lessonService.findLesson(enrollLessonDTO.getLessonName());
        Optional<Student> studentHandler = this.studentRepository.findById(enrollLessonDTO.getStudentNo());
        if (studentHandler.isPresent())
            return this.studentLessonService.enrollLesson(studentHandler.get(), lessonHandler.get());
        else
            throw new ApiRequestException("There is no student with given studentNo in database!");
    }

    @Override
    public Double calculateAverage (String studentNo) {
        Optional<Student> studentHandler = this.studentRepository.findById(studentNo);
        double scoreSum = 0.0;
        int gradeSum = 0;
        if (studentHandler.isPresent()) {
            List<StudentLesson> studentLessons = this.studentLessonService.findStudentLessons(studentHandler.get());
            if (studentLessons.size() == 0)
                throw new ApiRequestException("This student has no lessons in his/her enrolled lessons!");
            else {
                for (StudentLesson studentLesson : studentLessons) {
                    gradeSum += studentLesson.getLesson().getGradeNumber();
                    scoreSum += (studentLesson.getScore() * studentLesson.getLesson().getGradeNumber());
                }
                return scoreSum / gradeSum;
            }
        } else
            throw new ApiRequestException("There is no student with given studentNo in the database!");
    }
}
