package com.schoolmanagement.model.dto.faculty;

import javax.validation.constraints.NotNull;

public class UpdateFacultyDTO {
    @NotNull (message = "The id is needed to update the faculty!")
    private Integer id;
    @NotNull (message = "The faculty name can't be null!")
    private String name;

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
}
