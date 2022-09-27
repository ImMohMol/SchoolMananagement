package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.faculty.CreateFacultyDTO;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.model.dto.faculty.UpdateFacultyDTO;
import com.schoolmanagement.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping ("/paginated")
    public ResponseEntity<Response> getAllFacultiesPaginated (@RequestParam ("page") int page,
                                                              @RequestParam ("size") int size) {
        List<ReadFacultiesDTO> faculties = this.facultyService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, faculties,
                true));
    }

    @PostMapping (path = "")
    public ResponseEntity<Response> createNewFaculty (@RequestBody @Valid CreateFacultyDTO createFacultyDTO) {
        this.facultyService.create(createFacultyDTO);
        return ResponseEntity.ok(new Response(null, "Faculty created successfully!", null, true));
    }

    @PutMapping (path = "")
    public ResponseEntity<Response> updateFaculty (@RequestBody @Valid UpdateFacultyDTO updateFacultyDTO) {
        this.facultyService.update(updateFacultyDTO);
        return ResponseEntity.ok(new Response(null, "Faculty updated successfully!", null, true));
    }

    @DeleteMapping (path = "")
    public ResponseEntity<Response> deleteFaculty (@RequestParam ("facultyId") Long facultyId) {
        this.facultyService.delete(facultyId);
        return ResponseEntity.ok(new Response(null, "Faculty delete successfully!", null, true));
    }
}
