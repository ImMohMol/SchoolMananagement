package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.constant.LessonMessageGenerator;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.model.dto.lesson.UpdateLessonDTO;
import com.schoolmanagement.service.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping (path = GeneralConstantValues.API_BASE_URL + "/lessons")
public class LessonController {
    private final ILessonService lessonService;

    @Autowired
    public LessonController (ILessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping (path = "")
    public ResponseEntity<Response> getAllLessons () {
        List<ReadLessonsDTO> result = this.lessonService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, result, true));
    }

    @GetMapping ("/paginated")
    public ResponseEntity<Response> getAllLessonsPaginated (@RequestParam ("page") int page,
                                                            @RequestParam ("size") int size) {
        List<ReadLessonsDTO> lessons = this.lessonService.readPaginated(page, size);
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, lessons, true));
    }

    @PostMapping (path = "")
    public ResponseEntity<Response> createNewLesson (@RequestBody @Valid CreateLessonDTO createLessonDTO) {
        this.lessonService.create(createLessonDTO);
        return ResponseEntity.ok(new Response(null,
                LessonMessageGenerator.createLessonCreatedMessage(createLessonDTO.getName()), null,
                true));
    }

    @PutMapping (path = "")
    public ResponseEntity<Response> updateLesson (@RequestBody @Valid UpdateLessonDTO updateLessonDTO) {
        this.lessonService.update(updateLessonDTO);
        return ResponseEntity.ok(new Response(null,
                LessonMessageGenerator.createLessonUpdatedMessage(updateLessonDTO.getName()), null,
                true));
    }

    @DeleteMapping (path = "")
    public ResponseEntity<Response> deleteLesson (@RequestParam (name = "lessonName") String lessonName) {
        this.lessonService.delete(lessonName);
        return ResponseEntity.ok(new Response(null, LessonMessageGenerator.createLessonDeletedMessage(lessonName), null,
                true));
    }
}
