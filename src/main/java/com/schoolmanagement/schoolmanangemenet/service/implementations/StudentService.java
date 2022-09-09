package com.schoolmanagement.schoolmanangemenet.service.implementations;

import com.schoolmanagement.schoolmanangemenet.model.Student;
import com.schoolmanagement.schoolmanangemenet.repository.IStudentRepository;
import com.schoolmanagement.schoolmanangemenet.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    private IStudentRepository studentRepository;

    @Autowired
    public StudentService (IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Boolean create (Student object) {
        return null;
    }

    @Override
    public List<Student> read () {
        return null;
    }

    @Override
    public Boolean update (Student object) {
        return null;
    }

    @Override
    public Boolean delete (Student object) {
        return null;
    }
}
