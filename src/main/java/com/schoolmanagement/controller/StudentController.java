package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.constant.StudentConstantValues;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.student.*;
import com.schoolmanagement.service.interfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response> getAllStudents () {
        List<ReadStudentsDTO> students = this.studentService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, students, true));
    }

    @GetMapping (path = "/paginated")
    public ResponseEntity<Response> getAllStudentsPaginated (@RequestParam (name = "page") int page,
                                                             @RequestParam (name = "size") int size) {
        List<ReadStudentsDTO> students = this.studentService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, students, true));
    }

    @PostMapping (path = "")
    public ResponseEntity<Response> createNewStudent (@RequestBody @Valid CreateStudentDTO createStudentDTO) {
        this.studentService.create(createStudentDTO);
        return ResponseEntity.ok(new Response(null, StudentConstantValues.STUDENT_CREATED_SUCCESSFULLY, null, true));
    }

    @DeleteMapping (path = "")
    public ResponseEntity<Response> deleteStudent (@RequestBody @Valid DeleteStudentDTO deleteStudentDTO) {
        this.studentService.delete(deleteStudentDTO);
        return ResponseEntity.ok(new Response(null, StudentConstantValues.STUDENT_DELETED_SUCCESSFULLY, null, true));
    }

    @PutMapping (path = "")
    public ResponseEntity<Response> updateStudent (@RequestBody @Valid UpdateStudentDTO updateStudentDTO) {
        this.studentService.update(updateStudentDTO);
        return ResponseEntity.ok(new Response(null, StudentConstantValues.STUDENT_UPDATED_SUCCESSFULLY, null, true));
    }

    @PostMapping (path = "/lessons")
    public ResponseEntity<Response> enrollLesson (@RequestBody @Valid EnrollLessonDTO enrollLessonDTO) {
        this.studentService.enrollLesson(enrollLessonDTO);
        return ResponseEntity.ok(new Response(null, String.format("The lesson (%s) added to this student lessons " +
                "successfully!", enrollLessonDTO.getLessonName()),
                null,
                true));
    }
}
