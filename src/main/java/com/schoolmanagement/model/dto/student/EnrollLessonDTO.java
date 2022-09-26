package com.schoolmanagement.model.dto.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EnrollLessonDTO {
    @NotNull (message = "The studentNo can't be null!")
    @Size (min = 7, max = 7, message = "The studentNo must be 7 digits!")
    private String studentNo;
    @NotNull (message = "The lesson name can't be null!")
    private String lessonName;

    public String getStudentNo () {
        return studentNo;
    }

    public void setStudentNo (String studentNo) {
        this.studentNo = studentNo;
    }

    public String getLessonName () {
        return lessonName;
    }

    public void setLessonName (String lessonName) {
        this.lessonName = lessonName;
    }
}
