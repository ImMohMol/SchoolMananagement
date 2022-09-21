package com.schoolmanagement.schoolmanangemenet.controller;

import com.schoolmanagement.schoolmanangemenet.constant.GeneralConstantValues;
import com.schoolmanagement.schoolmanangemenet.model.Response;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.UpdateLessonDTO;
import com.schoolmanagement.schoolmanangemenet.service.interfaces.ILessonService;
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
}
