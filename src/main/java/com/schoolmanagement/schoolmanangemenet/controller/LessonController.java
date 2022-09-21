package com.schoolmanagement.schoolmanangemenet.controller;

import com.schoolmanagement.schoolmanangemenet.constant.GeneralConstantValues;
import com.schoolmanagement.schoolmanangemenet.model.Response;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.schoolmanangemenet.service.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (path = GeneralConstantValues.API_BASE_URL + "/lessons")
public class LessonController {
    private final ILessonService lessonService;

    @Autowired
    public LessonController (ILessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping(path = "")
    public ResponseEntity<Response> getAllLessons() {
        List<ReadLessonsDTO> result = this.lessonService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE, result, true));
    }
}
