package com.schoolmanagement.schoolmanangemenet.model.dto.lesson;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreateLessonDTO {
    @NotNull (message = "The lesson name can't be null!")
    private String name;
    @NotNull (message = "The lesson grade can't be null!")
    @Min (value = 1, message = "The grade number min value is 1!")
    @Max (value = 4, message = "The grade number max value is 4!")
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
