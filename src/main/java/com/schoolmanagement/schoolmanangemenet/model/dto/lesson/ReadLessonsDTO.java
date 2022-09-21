package com.schoolmanagement.schoolmanangemenet.model.dto.lesson;

public class ReadLessonsDTO {
    private String name;
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
