package com.schoolmanagement.schoolmanangemenet.service.interfaces;

import com.schoolmanagement.schoolmanangemenet.model.dto.student.CreateStudentDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.DeleteStudentDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.student.UpdateStudentDTO;

import java.util.List;

public interface IStudentService {
    Boolean create (CreateStudentDTO createStudentDTO);

    List<ReadStudentsDTO> read ();

    Boolean update (UpdateStudentDTO updateStudentDTO);

    Boolean delete (DeleteStudentDTO deleteStudentDTO);
}
