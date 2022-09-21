package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (path = GeneralConstantValues.API_BASE_URL + "/faculties")
public class FacultyController {
    private final IFacultyService facultyService;

    @Autowired
    public FacultyController (IFacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping (path = "")
    public ResponseEntity<Response> getAllFaculties () {
        List<ReadFacultiesDTO> receivedFaculties = this.facultyService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE,
                receivedFaculties, true));
    }
}
