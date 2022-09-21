package com.schoolmanagement.service.implementations;

import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.model.dto.lesson.UpdateLessonDTO;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.repository.ILessonRepository;
import com.schoolmanagement.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.service.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService implements ILessonService {
    private final ILessonRepository lessonRepository;

    @Autowired
    public LessonService (ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public Boolean create (CreateLessonDTO createlessonDTO) {
        Optional<Lesson> handler = this.lessonRepository.findByName(createlessonDTO.getName());
        if (handler.isPresent()) {
            throw new ApiRequestException("This lesson exists in the database and can't add it again!");
        } else {
            this.lessonRepository.save(GeneralMapper.convert(createlessonDTO, Lesson.class));
            return true;
        }
    }

    @Override
    public List<ReadLessonsDTO> read () {
        List<ReadLessonsDTO> receivedLessons = new ArrayList<>();
        this.lessonRepository.findAll().forEach((lesson -> receivedLessons.add(GeneralMapper.convert(lesson, ReadLessonsDTO.class))));
        return receivedLessons;
    }

    @Override
    public Boolean update (UpdateLessonDTO updateLessonDTO) {
        Optional<Lesson> handler = this.lessonRepository.findById(updateLessonDTO.getId());
        if (handler.isPresent()) {
            this.lessonRepository.save(GeneralMapper.convert(updateLessonDTO, Lesson.class));
            return true;
        } else {
            throw new ApiRequestException("This lesson does not exist in the database!");
        }
    }

    @Override
    public Boolean delete (Long lessonId) {
        Optional<Lesson> handler = this.lessonRepository.findById(lessonId);
        if (handler.isPresent()) {
            this.lessonRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException("This lesson does not exist in the database!");
        }
    }
}
