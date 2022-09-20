package com.schoolmanagement.schoolmanangemenet.service.implementations;

import com.schoolmanagement.schoolmanangemenet.model.Student;
import com.schoolmanagement.schoolmanangemenet.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.CreateStudentDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.DeleteStudentDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.UpdateStudentDTO;
import com.schoolmanagement.schoolmanangemenet.repository.IStudentRepository;
import com.schoolmanagement.schoolmanangemenet.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;

    @Autowired
    public StudentService (IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Boolean create (CreateStudentDTO createStudentDTO) {
        // finding the student using the student nationalCode!
        Optional<Student> handler = this.studentRepository.findByNationalCode(createStudentDTO.getNationalCode());
        if (handler.isPresent()) {
            throw new DuplicateKeyException("This student exists in the database and cant insert it again!");
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

    }

    @Override
    public Boolean delete (DeleteStudentDTO deleteStudentDTO) {

    }
}
