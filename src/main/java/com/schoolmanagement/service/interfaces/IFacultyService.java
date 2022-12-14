package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.dto.faculty.CreateFacultyDTO;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.model.dto.faculty.UpdateFacultyDTO;

import java.util.List;

public interface IFacultyService {
    Boolean create (CreateFacultyDTO createFacultyDTO);

    List<ReadFacultiesDTO> read ();

    List<ReadFacultiesDTO> readPaginated(int page, int size);

    Boolean update (UpdateFacultyDTO updateFacultyDTO);

    Boolean delete (String facultyName);
}
