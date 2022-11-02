package com.schoolmanagement.service.implementation;

import com.schoolmanagement.utils.FacultyMessageGenerator;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return this.facultyRepository.findAll().stream()
                .map(faculty -> ((ReadFacultiesDTO) GeneralMapper.convert(faculty, ReadFacultiesDTO.class)))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadFacultiesDTO> readPaginated (int page, int size) {
        List<Faculty> faculties = this.facultyRepository.findAll(PageRequest.of(page, size)).toList();
        return faculties.stream()
                .map(faculty -> (ReadFacultiesDTO) GeneralMapper.convert(faculty, ReadFacultiesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean update (UpdateFacultyDTO updateFacultyDTO) {
        Optional<Faculty> handler = this.facultyRepository.findById(updateFacultyDTO.getId());
        if (handler.isPresent()) {
            this.facultyRepository.save(GeneralMapper.convert(updateFacultyDTO, Faculty.class));
            return true;
        } else {
            throw new ApiRequestException(FacultyMessageGenerator.createFacultyDoesNotExistsMessage(updateFacultyDTO.getName()));
        }
    }

    @Override
    public Boolean delete (String facultyName) {
        Optional<Faculty> handler = this.facultyRepository.findByName(facultyName);
        if (handler.isPresent()) {
            this.facultyRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException(FacultyMessageGenerator.createFacultyDoesNotExistsMessage(facultyName));
        }
    }
}
