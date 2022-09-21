package com.schoolmanagement.controller;

import com.schoolmanagement.constant.GeneralConstantValues;
import com.schoolmanagement.model.Response;
import com.schoolmanagement.model.dto.teacher.ReadTeachersDTO;
import com.schoolmanagement.service.interfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Response> getAllTeachers () {
        List<ReadTeachersDTO> teachers = this.teacherService.read();
        return ResponseEntity.ok(new Response(null, GeneralConstantValues.SUCCESSFUL_OPERATION_MESSAGE,
                teachers, true));
    }
}
