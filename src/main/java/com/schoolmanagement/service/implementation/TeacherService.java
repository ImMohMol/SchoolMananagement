package com.schoolmanagement.service.implementation;

import com.schoolmanagement.utils.TeacherMessageGenerator;
import com.schoolmanagement.utils.Utils;
import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.Teacher;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.teacher.CreateTeacherDTO;
import com.schoolmanagement.model.dto.teacher.ReadTeachersDTO;
import com.schoolmanagement.model.dto.teacher.UpdateTeacherDTO;
import com.schoolmanagement.repository.ITeacherRepository;
import com.schoolmanagement.service.interfaces.IStudentService;
import com.schoolmanagement.service.interfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;
    private final IStudentService studentService;

    @Autowired
    public TeacherService (ITeacherRepository teacherRepository, IStudentService studentService) {
        this.teacherRepository = teacherRepository;
        this.studentService = studentService;
    }

    @Override
    public Boolean create (CreateTeacherDTO createTeacherDTO) {
        Optional<Teacher> handler = this.teacherRepository.findByPersonalNo(createTeacherDTO.getPersonalNo());
        if (handler.isPresent()) {
            throw new ApiRequestException(TeacherMessageGenerator.createTeacherExistsMessage(createTeacherDTO.getFirstName(), createTeacherDTO.getLastName()));
        } else {
            this.teacherRepository.save(GeneralMapper.convert(createTeacherDTO, Teacher.class));
            return true;
        }
    }

    @Override
    public List<ReadTeachersDTO> read () {
        return this.teacherRepository.findAll().stream()
                .map(teacher -> (ReadTeachersDTO) GeneralMapper.convert(teacher, ReadTeachersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadTeachersDTO> readPaginated (int page, int size) {
        List<Teacher> teachers = this.teacherRepository.findAll(PageRequest.of(page, size)).toList();
        return teachers.stream()
                .map(teacher -> (ReadTeachersDTO) GeneralMapper.convert(teacher, ReadTeachersDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean update (UpdateTeacherDTO updateTeacherDTO) {
        Optional<Teacher> handler = this.teacherRepository.findByPersonalNo(updateTeacherDTO.getPersonalNo());
        if (handler.isPresent()) {
            this.teacherRepository.save(GeneralMapper.convert(updateTeacherDTO, Teacher.class));
            return true;
        } else {
            throw new ApiRequestException(TeacherMessageGenerator.createTeacherDoesNotExistMessage(updateTeacherDTO.getFirstName(), updateTeacherDTO.getLastName()));
        }
    }

    @Override
    public Boolean delete (String personalNo) {
        Optional<Teacher> handler = this.teacherRepository.findByPersonalNo(personalNo);
        if (handler.isPresent()) {
            this.teacherRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException(TeacherMessageGenerator.createTeacherDoesNotExistMessage(personalNo));
        }
    }

    @Override
    public List<ReadStudentsDTO> getStudentsList (String personalNo) {
        Optional<Teacher> teacherHandler = this.teacherRepository.findByPersonalNo(personalNo);
        if (teacherHandler.isPresent()) {
            return teacherHandler.get().getStudents().stream()
                    .map(student -> (ReadStudentsDTO) GeneralMapper.convert(student, ReadStudentsDTO.class))
                    .collect(Collectors.toList());
        } else {
            throw new ApiRequestException(TeacherMessageGenerator.createTeacherDoesNotExistMessage(personalNo));
        }
    }

    @Override
    public Double calculateStudentsAverage (String personalNo) {
        Optional<Teacher> teacherHandler = this.teacherRepository.findByPersonalNo(personalNo);
        if (teacherHandler.isPresent()) {
            AtomicReference<Double> result = new AtomicReference<>(0.0);
            List<Student> students = teacherHandler.get().getStudents();
            if (students.size() != 0) {
                students.stream()
                        .peek(student -> result.updateAndGet(v -> v + this.studentService.calculateAverage(student.getStudentNo())));
                return Utils.formatDoubleNumber(result.get() / students.size());
            } else
                throw new ApiRequestException(TeacherMessageGenerator.createTeacherHasNoStudentsMessage(personalNo));
        } else throw new ApiRequestException(TeacherMessageGenerator.createTeacherDoesNotExistMessage(personalNo));
    }
}
