package com.schoolmanagement.controller;

import com.schoolmanagement.utils.FacultyMessageGenerator;
import com.schoolmanagement.utils.GeneralConstantValues;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.faculty.CreateFacultyDTO;
import com.schoolmanagement.model.dto.faculty.ReadFacultiesDTO;
import com.schoolmanagement.model.dto.faculty.UpdateFacultyDTO;
import com.schoolmanagement.service.interfaces.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize ("hasAuthority('faculty:read')")
    public ResponseEntity<Response> getAllFaculties () {
        List<ReadFacultiesDTO> receivedFaculties = this.facultyService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE,
                receivedFaculties, true));
    }

    @GetMapping ("/paginated/{page}/{size}")
    @PreAuthorize ("hasAuthority('faculty:read')")
    public ResponseEntity<Response> getAllFacultiesPaginated (@PathVariable (name = "page") int page,
                                                              @PathVariable (name = "size") int size) {
        List<ReadFacultiesDTO> faculties = this.facultyService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, faculties,
                true));
    }

    @PostMapping (path = "")
    @PreAuthorize ("hasAuthority('faculty:write')")
    public ResponseEntity<Response> createNewFaculty (@RequestBody @Valid CreateFacultyDTO createFacultyDTO) {
        this.facultyService.create(createFacultyDTO);
        return ResponseEntity.ok(new Response(null,
                FacultyMessageGenerator.createFacultyCreatedMessage(createFacultyDTO.getName()), null,
                true));
    }

    @PutMapping (path = "")
    @PreAuthorize ("hasAuthority('faculty:write')")
    public ResponseEntity<Response> updateFaculty (@RequestBody @Valid UpdateFacultyDTO updateFacultyDTO) {
        this.facultyService.update(updateFacultyDTO);
        return ResponseEntity.ok(new Response(null,
                FacultyMessageGenerator.createFacultyUpdatedMessage(updateFacultyDTO.getName()), null,
                true));
    }

    @DeleteMapping (path = "/{facultyName}")
    @PreAuthorize ("hasAuthority('faculty:write')")
    public ResponseEntity<Response> deleteFaculty (@PathVariable (name = "facultyName") String facultyName) {
        this.facultyService.delete(facultyName);
        return ResponseEntity.ok(new Response(null, FacultyMessageGenerator.createFacultyDeletedMessage(facultyName), null,
                true));
    }
}
