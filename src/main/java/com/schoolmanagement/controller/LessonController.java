package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
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

    @PostMapping (path = "")
    public ResponseEntity<Response> createNewLesson (@RequestBody @Valid CreateLessonDTO createLessonDTO) {
        this.lessonService.create(createLessonDTO);
        return ResponseEntity.ok(new Response(null, "Lesson created successfully!", null, true));
    }

    @PutMapping (path = "")
    public ResponseEntity<Response> updateLesson (@RequestBody @Valid UpdateLessonDTO updateLessonDTO) {
        this.lessonService.update(updateLessonDTO);
        return ResponseEntity.ok(new Response(null, "Lesson updated successfully!", null, true));
    }

    @DeleteMapping (path = "")
    public ResponseEntity<Response> deleteLesson (@RequestParam(name = "lessonId") Long lessonId) {
        this.lessonService.delete(lessonId);
        return ResponseEntity.ok(new Response(null, "Lesson delete successfully!", null, true));
    }
}
