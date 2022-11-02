package com.schoolmanagement.controller;

import com.schoolmanagement.utils.GeneralConstantValues;
import com.schoolmanagement.utils.StudentMessageGenerator;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.student.CreateStudentDTO;
import com.schoolmanagement.model.dto.student.EnrollLessonDTO;
import com.schoolmanagement.model.dto.student.ReadStudentsDTO;
import com.schoolmanagement.model.dto.student.UpdateStudentDTO;
import com.schoolmanagement.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (path = GeneralConstantValues.API_BASE_URL + "/students")
public class StudentController {
    private final IStudentService studentService;

    @Autowired
    public StudentController (IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping (path = "")
    @PreAuthorize ("hasAuthority('student:read')")
    public ResponseEntity<Response> getAllStudents () {
        List<ReadStudentsDTO> students = this.studentService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, students, true));
    }

    @GetMapping (path = "/paginated")
    @PreAuthorize ("hasAuthority('student:read')")
    public ResponseEntity<Response> getAllStudentsPaginated (@RequestParam (name = "page") int page,
                                                             @RequestParam (name = "size") int size) {
        List<ReadStudentsDTO> students = this.studentService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, students, true));
    }

    @PostMapping (path = "")
    @PreAuthorize ("hasAuthority('student:write')")
    public ResponseEntity<Response> createNewStudent (@RequestBody @Valid CreateStudentDTO createStudentDTO) {
        this.studentService.create(createStudentDTO);
        return ResponseEntity.ok(new Response(null,
                StudentMessageGenerator.createStudentCreatedMessage(createStudentDTO.getFirstName(),
                        createStudentDTO.getLastName()), null,
                true));
    }

    @DeleteMapping (path = "")
    @PreAuthorize ("hasAuthority('student:write')")
    public ResponseEntity<Response> deleteStudent (@RequestParam ("studentNo") String studentNo) {
        this.studentService.delete(studentNo);
        return ResponseEntity.ok(new Response(null, StudentMessageGenerator.createStudentDeletedMessage(studentNo), null,
                true));
    }

    @PutMapping (path = "")
    @PreAuthorize ("hasAuthority('student:write')")
    public ResponseEntity<Response> updateStudent (@RequestBody @Valid UpdateStudentDTO updateStudentDTO) {
        this.studentService.update(updateStudentDTO);
        return ResponseEntity.ok(new Response(null,
                StudentMessageGenerator.createStudentUpdateddMessage(updateStudentDTO.getFirstName(),
                        updateStudentDTO.getLastName()), null,
                true));
    }

    @PostMapping (path = "/lessons")
    @PreAuthorize ("hasAuthority('student:write')")
    public ResponseEntity<Response> enrollLesson (@RequestBody @Valid EnrollLessonDTO enrollLessonDTO) {
        this.studentService.enrollLesson(enrollLessonDTO);
        return ResponseEntity.ok(new Response(null, String.format("The lesson (%s) added to this student lessons " +
                "successfully!", enrollLessonDTO.getLessonName()),
                null,
                true));
    }

    @GetMapping ("/average")
    @PreAuthorize ("hasAuthority('student:read')")
    public ResponseEntity<Response> calculateAverage (@RequestParam ("studentNo") String studentNo) {
        Double average = this.studentService.calculateAverage(studentNo);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, average, true));
    }
}
