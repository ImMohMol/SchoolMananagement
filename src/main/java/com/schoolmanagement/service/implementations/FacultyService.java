package com.schoolmanagement.service.implementations;

import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Faculty;
import com.schoolmanagement.model.dto.faculty.CreateFacultyDTO;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.model.dto.faculty.UpdateFacultyDTO;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.repository.IFacultyRepository;
import com.schoolmanagement.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Autowired
    public FacultyService (IFacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Boolean create (CreateFacultyDTO createFacultyDTO) {
        Optional<Faculty> handler = this.facultyRepository.findByName(createFacultyDTO.getName());
        if (handler.isPresent()) {
            throw new ApiRequestException("This faculty already exists in the database!");
        } else {
            this.facultyRepository.save(GeneralMapper.convert(createFacultyDTO, Faculty.class));
            return true;
        }
    }

    @Override
    public List<ReadFacultiesDTO> read () {
        List<ReadFacultiesDTO> receivedFaculties = new ArrayList<>();
        this.facultyRepository.findAll().forEach(faculty -> receivedFaculties.add(GeneralMapper.convert(faculty, ReadFacultiesDTO.class)));
        return receivedFaculties;
    }

    @Override
    public Boolean update (UpdateFacultyDTO updateFacultyDTO) {
        Optional<Faculty> handler = this.facultyRepository.findById(updateFacultyDTO.getId());
        if (handler.isPresent()) {
            this.facultyRepository.save(GeneralMapper.convert(updateFacultyDTO, Faculty.class));
            return true;
        } else {
            throw new ApiRequestException("This faculty does not exist in the database!");
        }
    }

    @Override
    public Boolean delete (Integer facultyId) {
        Optional<Faculty> handler = this.facultyRepository.findById(facultyId);
        if (handler.isPresent()) {
            this.facultyRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException("There is no faculty with this id in the database!");
        }
    }
}
