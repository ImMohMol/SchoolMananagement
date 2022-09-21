package com.schoolmanagement.service.implementations;

import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.model.dto.student.DeleteStudentDTO;
import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.student.UpdateStudentDTO;
import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Student;
import com.schoolmanagement.model.dto.student.CreateStudentDTO;
import com.schoolmanagement.repository.IStudentRepository;
import com.schoolmanagement.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService (IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
}
