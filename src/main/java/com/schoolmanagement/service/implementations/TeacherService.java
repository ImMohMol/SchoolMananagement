package com.schoolmanagement.service.implementations;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Teacher> handler = this.teacherRepository.findById(createTeacherDTO.getPersonalNo());
        if (handler.isPresent()) {
            throw new ApiRequestException("This teacher already exists in the database!");
        } else {
            this.teacherRepository.save(GeneralMapper.convert(createTeacherDTO, Teacher.class));
            return true;
        }
    }

    @Override
    public List<ReadTeachersDTO> read () {
        List<ReadTeachersDTO> receivedTeachers = new ArrayList<>();
        this.teacherRepository.findAll().forEach((teacher -> receivedTeachers.add(GeneralMapper.convert(teacher, ReadTeachersDTO.class))));
        return receivedTeachers;
    }

    @Override
    public List<ReadTeachersDTO> readPaginated (int page, int size) {
        List<Teacher> teachers = this.teacherRepository.findAll(PageRequest.of(page, size)).toList();
        List<ReadTeachersDTO> result = new ArrayList<>();
        teachers.forEach((teacher -> result.add(GeneralMapper.convert(teacher, ReadTeachersDTO.class))));
        return result;
    }

    @Override
    public Boolean update (UpdateTeacherDTO updateTeacherDTO) {
        Optional<Teacher> handler = this.teacherRepository.findById(updateTeacherDTO.getPersonalNo());
        if (handler.isPresent()) {
            this.teacherRepository.save(GeneralMapper.convert(updateTeacherDTO, Teacher.class));
            return true;
        } else {
            throw new ApiRequestException("This teacher does not exist in the database!");
        }
    }

    @Override
    public Boolean delete (String personalNo) {
        Optional<Teacher> handler = this.teacherRepository.findById(personalNo);
        if (handler.isPresent()) {
            this.teacherRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException("There is no teacher in the database with given personalNo!");
        }
    }

    @Override
    public List<ReadStudentsDTO> getStudentsList (String personalNo) {
        Optional<Teacher> teacherHandler = this.teacherRepository.findById(personalNo);
        if (teacherHandler.isPresent()) {
            List<ReadStudentsDTO> students = new ArrayList<>();
            teacherHandler.get().getStudents().forEach((student -> students.add(GeneralMapper.convert(student, ReadStudentsDTO.class))));
            return students;
        } else
            throw new ApiRequestException("There is no teacher with given personalNo in the database!");
    }

    @Override
    public Double calculateStudentsAverage (String personalNo) {
        Optional<Teacher> teacherHandler = this.teacherRepository.findById(personalNo);
        if (teacherHandler.isPresent()) {
            double result = 0.0;
            List<Student> students = teacherHandler.get().getStudents();
            for (Student student : students)
                result += this.studentService.calculateAverage(student.getStudentNo());
            return result / students.size();
        } else
            throw new ApiRequestException("There is no teacher with given personalNo in the database!");
    }
}
