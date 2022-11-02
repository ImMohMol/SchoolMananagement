package com.schoolmanagement.service.implementation;

import com.schoolmanagement.utils.LessonMessageGenerator;
import com.schoolmanagement.exception.ApiRequestException;
import com.schoolmanagement.model.Lesson;
import com.schoolmanagement.model.dto.lesson.CreateLessonDTO;
import com.schoolmanagement.model.dto.lesson.ReadLessonsDTO;
import com.schoolmanagement.model.dto.lesson.UpdateLessonDTO;
import com.schoolmanagement.model.dto.mapper.GeneralMapper;
import com.schoolmanagement.repository.ILessonRepository;
import com.schoolmanagement.service.interfaces.ILessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            throw new ApiRequestException(LessonMessageGenerator.createLessonExistsMessage(createlessonDTO.getName()));
        } else {
            this.lessonRepository.save(GeneralMapper.convert(createlessonDTO, Lesson.class));
            return true;
        }
    }

    @Override
    public List<ReadLessonsDTO> read () {
        return this.lessonRepository.findAll()
                .stream()
                .map(lesson -> (ReadLessonsDTO) GeneralMapper.convert(lesson, ReadLessonsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReadLessonsDTO> readPaginated (int page, int size) {
        List<Lesson> lessons = this.lessonRepository.findAll(PageRequest.of(page, size)).toList();
        return lessons.stream()
                .map(lesson -> (ReadLessonsDTO) GeneralMapper.convert(lesson, ReadLessonsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Boolean update (UpdateLessonDTO updateLessonDTO) {
        Optional<Lesson> handler = this.lessonRepository.findById(updateLessonDTO.getId());
        if (handler.isPresent()) {
            this.lessonRepository.save(GeneralMapper.convert(updateLessonDTO, Lesson.class));
            return true;
        } else {
            throw new ApiRequestException(LessonMessageGenerator.createLessonDoesNotExistMessage(updateLessonDTO.getName()));
        }
    }

    @Override
    public Boolean delete (String lessonName) {
        Optional<Lesson> handler = this.lessonRepository.findByName(lessonName);
        if (handler.isPresent()) {
            this.lessonRepository.delete(handler.get());
            return true;
        } else {
            throw new ApiRequestException(LessonMessageGenerator.createLessonDoesNotExistMessage(lessonName));
        }
    }

    @Override
    public Optional<Lesson> findLesson (String lessonName) {
        Optional<Lesson> handler = this.lessonRepository.findByName(lessonName);
        if (handler.isPresent()) return handler;
        throw new ApiRequestException(LessonMessageGenerator.createLessonDoesNotExistMessage(lessonName));
    }
}
