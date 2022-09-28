package com.schoolmanagement.service.implementations;

import com.schoolmanagement.constant.FacultyMessageGenerator;
import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Faculty;
import com.schoolmanagement.model.dto.faculty.CreateFacultyDTO;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.model.dto.faculty.UpdateFacultyDTO;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.repository.IFacultyRepository;
import com.schoolmanagement.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
            throw new ApiRequestException(FacultyMessageGenerator.createDuplicateFacultyMessage(createFacultyDTO.getName()));
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
    public List<ReadFacultiesDTO> readPaginated (int page, int size) {
        List<Faculty> faculties = this.facultyRepository.findAll(PageRequest.of(page, size)).toList();
        List<ReadFacultiesDTO> result = new ArrayList<>();
        faculties.forEach((faculty -> result.add(GeneralMapper.convert(faculty, ReadFacultiesDTO.class))));
        return result;
    }

    @Override
    public Boolean update (UpdateFacultyDTO updateFacultyDTO) {
        Optional<Faculty> handler = this.facultyRepository.findById(updateFacultyDTO.getId());
        if (handler.isPresent()) {
            this.facultyRepository.save(GeneralMapper.convert(updateFacultyDTO, Faculty.class));
            return true;
        } else {
            throw new ApiRequestException(FacultyMessageGenerator.createFacultyDoesNotExists(updateFacultyDTO.getName()));
        }
    }

    @Override
    public Boolean delete (Long facultyId) {
        Optional<Faculty> handler = this.facultyRepository.findById(facultyId);
        if (handler.isPresent()) {
            this.facultyRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException(FacultyMessageGenerator.createFacultyDoesNotExists(facultyId));
        }
    }
}
