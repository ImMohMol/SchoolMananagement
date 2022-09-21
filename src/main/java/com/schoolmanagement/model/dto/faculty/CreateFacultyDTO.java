package com.schoolmanagement.model.dto.faculty;

import javax.validation.constraints.NotNull;

public class CreateFacultyDTO {
    @NotNull (message = "The faculty name can't be null!")
    private String name;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
