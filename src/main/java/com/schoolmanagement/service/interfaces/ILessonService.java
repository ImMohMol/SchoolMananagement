package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.model.dto.lesson.UpdateLessonDTO;

import java.util.List;
import java.util.Optional;

public interface ILessonService {
    Boolean create (CreateLessonDTO createlessonDTO);

    List<ReadLessonsDTO> read ();

    Boolean update (UpdateLessonDTO updateLessonDTO);

    Boolean delete (Long lessonId);

    Optional<Lesson> findLesson (String lessonName);
}
