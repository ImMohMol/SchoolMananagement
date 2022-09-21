package com.schoolmanagement.service.implementations;

import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Teacher;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.model.dto.teacher.ReadTeachersDTO;
import com.schoolmanagement.repository.ITeacherRepository;
import com.schoolmanagement.model.dto.teacher.CreateTeacherDTO;
import com.schoolmanagement.model.dto.teacher.UpdateTeacherDTO;
import com.schoolmanagement.service.interfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;

    @Autowired
    public TeacherService (ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Boolean create (CreateTeacherDTO createTeacherDTO) {
        Optional<Teacher> handler = this.teacherRepository.findById(createTeacherDTO.getPersonalNo());
        if (handler.isPresent()) {
            throw new ApiRequestException("This teacher exists in the database!");
        } else {
            this.teacherRepository.save(GeneralMapper.convert(createTeacherDTO, Teacher.class));
            return true;
        }
    }

    @Override
    public List<ReadTeachersDTO> read () {
        return null;
    }

    @Override
    public Boolean update (UpdateTeacherDTO updateTeacherDTO) {
        return null;
    }

    @Override
    public Boolean delete (String personalNo) {
        return null;
    }
}
