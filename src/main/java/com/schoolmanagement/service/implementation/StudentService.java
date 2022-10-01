package com.schoolmanagement.service.implementation;

import com.schoolmanagement.constant.StudentMessageGenerator;
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
        if (handler.isPresent())
            throw new ApiRequestException(StudentMessageGenerator.createStudentExistsMessage(createStudentDTO.getFirstName(), createStudentDTO.getLastName()));
        else {
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
        Optional<Student> handler = this.studentRepository.findByStudentNo(updateStudentDTO.getStudentNo());
        if (handler.isPresent()) {
            this.studentRepository.save(Objects.requireNonNull(GeneralMapper.convert(updateStudentDTO, Student.class)));
            return true;
        } else
            throw new ApiRequestException(StudentMessageGenerator.createStudentDoesNotExistMessage(updateStudentDTO.getFirstName(), updateStudentDTO.getLastName()));
    }

    @Override
    public Boolean delete (String studentNo) {
        Optional<Student> handler = this.studentRepository.findByStudentNo(studentNo);
        if (handler.isPresent()) {
            this.studentRepository.delete(handler.get());
            return true;
        } else
            throw new ApiRequestException(StudentMessageGenerator.createStudentDoesNotExistMessage(studentNo));
    }

    @Override
    public void enrollLesson (EnrollLessonDTO enrollLessonDTO) {
        Optional<Lesson> lessonHandler = this.lessonService.findLesson(enrollLessonDTO.getLessonName());
        Optional<Student> studentHandler = this.studentRepository.findByStudentNo(enrollLessonDTO.getStudentNo());
        if (studentHandler.isPresent()) {
            this.studentLessonService.enrollLesson(studentHandler.get(), lessonHandler.get());
        } else
            throw new ApiRequestException(StudentMessageGenerator.createStudentDoesNotExistMessage(enrollLessonDTO.getStudentNo()));
    }

    @Override
    public Double calculateAverage (String studentNo) {
        Optional<Student> studentHandler = this.studentRepository.findByStudentNo(studentNo);
        double scoreSum = 0.0;
        int gradeSum = 0;
        if (studentHandler.isPresent()) {
            List<StudentLesson> studentLessons = this.studentLessonService.findStudentLessons(studentHandler.get());
            if (studentLessons.size() == 0)
                throw new ApiRequestException(StudentMessageGenerator.createStudentDoesNotHaveEnrolledLessonsMessage(studentNo));
            else {
                for (StudentLesson studentLesson : studentLessons) {
                    gradeSum += studentLesson.getLesson().getGradeNumber();
                    scoreSum += (studentLesson.getScore() * studentLesson.getLesson().getGradeNumber());
                }
                return scoreSum / gradeSum;
            }
        } else
            throw new ApiRequestException(StudentMessageGenerator.createStudentDoesNotExistMessage(studentNo));
    }
}
