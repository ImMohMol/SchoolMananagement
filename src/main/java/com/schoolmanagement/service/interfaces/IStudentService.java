package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.dto.student.DeleteStudentDTO;
import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.student.UpdateStudentDTO;
import com.schoolmanagement.model.dto.student.CreateStudentDTO;

import java.util.List;

public interface IStudentService {
    Boolean create (CreateStudentDTO createStudentDTO);

    List<ReadStudentsDTO> read ();

    Boolean update (UpdateStudentDTO updateStudentDTO);

    Boolean delete (DeleteStudentDTO deleteStudentDTO);
}
