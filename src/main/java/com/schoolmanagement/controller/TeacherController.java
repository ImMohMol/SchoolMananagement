package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.constant.TeacherMessageGenerator;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.teacher.CreateTeacherDTO;
import com.schoolmanagement.model.dto.teacher.ReadTeachersDTO;
import com.schoolmanagement.model.dto.teacher.UpdateTeacherDTO;
import com.schoolmanagement.service.interfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping (path = GeneralConstantValues.API_BASE_URL + "/teachers")
public class TeacherController {
    private final ITeacherService teacherService;

    @Autowired
    public TeacherController (ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping (path = "")
    @PreAuthorize ("hasAuthority('teacher:read')")
    public ResponseEntity<Response> getAllTeachers () {
        List<ReadTeachersDTO> teachers = this.teacherService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE,
                teachers, true));
    }

    @GetMapping (path = "/paginated")
    @PreAuthorize ("hasAuthority('teacher:read')")
    public ResponseEntity<Response> getAllTeachersPaginated (int page, int size) {
        List<ReadTeachersDTO> teachers = this.teacherService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, teachers, true));
    }

    @PostMapping (path = "")
    @PreAuthorize ("hasAuthority('teacher:write')")
    public ResponseEntity<Response> createNewTeacher (@RequestBody @Valid CreateTeacherDTO createTeacherDTO) {
        this.teacherService.create(createTeacherDTO);
        return ResponseEntity.ok(new Response(null,
                TeacherMessageGenerator.createTeacherCreatedMessage(createTeacherDTO.getFirstName(),
                        createTeacherDTO.getLastName()),
                null,
                true));
    }

    @PutMapping (path = "")
    @PreAuthorize ("hasAuthority('teacher:write')")
    public ResponseEntity<Response> updateTeacher (@RequestBody @Valid UpdateTeacherDTO updateTeacherDTO) {
        this.teacherService.update(updateTeacherDTO);
        return ResponseEntity.ok(new Response(null,
                TeacherMessageGenerator.createTeacherUpdatedMessage(updateTeacherDTO.getFirstName(),
                        updateTeacherDTO.getLastName()), null,
                true));
    }

    @DeleteMapping (path = "")
    @PreAuthorize ("hasAuthority('teacher:write')")
    public ResponseEntity<Response> deleteTeacher (@RequestParam (name = "personalNo") @NotBlank String personalNo) {
        this.teacherService.delete(personalNo);
        return ResponseEntity.ok(new Response(null, TeacherMessageGenerator.createTeacherDeletedMessage(personalNo), null,
                true));
    }

    @GetMapping ("/students")
    @PreAuthorize ("hasAuthority('teacher:read')")
    public ResponseEntity<Response> getTeacherStudents (@RequestParam ("personalNo") String personalNo) {
        List<ReadStudentsDTO> students = this.teacherService.getStudentsList(personalNo);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, students, true));
    }

    @GetMapping ("/students/average")
    @PreAuthorize ("hasAuthority('teacher:read')")
    public ResponseEntity<Response> calculateStudentsAverage (@RequestParam ("personalNo") String personalNo) {
        double result = this.teacherService.calculateStudentsAverage(personalNo);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, result, true));
    }
}
