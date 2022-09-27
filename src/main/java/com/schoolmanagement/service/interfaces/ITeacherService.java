package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.teacher.CreateTeacherDTO;
import com.schoolmanagement.model.dto.teacher.ReadTeachersDTO;
import com.schoolmanagement.model.dto.teacher.UpdateTeacherDTO;

import java.util.List;

public interface ITeacherService {
    Boolean create (CreateTeacherDTO createTeacherDTO);

    List<ReadTeachersDTO> read ();

    List<ReadTeachersDTO> readPaginated (int page, int size);

    Boolean update (UpdateTeacherDTO updateTeacherDTO);

    Boolean delete (String personalNo);

    List<ReadStudentsDTO> getStudentsList (String personalNo);
}
