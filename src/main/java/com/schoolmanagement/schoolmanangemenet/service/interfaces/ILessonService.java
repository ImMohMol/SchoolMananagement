package com.schoolmanagement.schoolmanangemenet.service.interfaces;

import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.schoolmanangemenet.model.dto.lesson.UpdateLessonDTO;

import java.util.List;

public interface ILessonService {
    Boolean create (CreateLessonDTO createlessonDTO);

    List<ReadLessonsDTO> read ();

    Boolean update (UpdateLessonDTO updateLessonDTO);

    Boolean delete (Long lessonId);
}
