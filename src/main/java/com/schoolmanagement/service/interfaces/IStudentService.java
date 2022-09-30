package com.schoolmanagement.service.interfaces;

import com.schoolmanagement.model.dto.student.*;

import java.util.List;

public interface IStudentService {
    Boolean create (CreateStudentDTO createStudentDTO);

    List<ReadStudentsDTO> read ();

    List<ReadStudentsDTO> readPaginated(int page, int size);

    Boolean update (UpdateStudentDTO updateStudentDTO);

    Boolean delete (String studentNo);

    void enrollLesson(EnrollLessonDTO enrollLessonDTO);

    Double calculateAverage(String studentNo);
}
