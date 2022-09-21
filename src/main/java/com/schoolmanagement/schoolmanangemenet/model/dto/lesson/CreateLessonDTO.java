package com.schoolmanagement.schoolmanangemenet.model.dto.lesson;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateLessonDTO {
    @NotNull (message = "The lesson name can't be null!")
    private String name;
    @NotNull (message = "The lesson grade can't be null!")
    @Size (min = 1, max = 4, message = "The lesson grade should be greater than 0 and lower than 5")
    private Integer gradeNumber;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getGradeNumber () {
        return gradeNumber;
    }

    public void setGradeNumber (Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }
}
