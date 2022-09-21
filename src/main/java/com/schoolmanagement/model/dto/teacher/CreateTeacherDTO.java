package com.schoolmanagement.model.dto.teacher;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateTeacherDTO {
    @NotNull (message = "The personalNo can't be null!")
    private String personalNo;
    @NotNull (message = "The firstName can't be null!")
    private String firstName;
    @NotNull (message = "The lastName can't be null!")
    private String lastName;
    @NotNull (message = "The nationalCode can't be null!")
    @Size (min = 10, max = 10, message = "The nationalCode must be 10 digits!")
    private String nationalCode;

    public String getPersonalNo () {
        return personalNo;
    }

    public void setPersonalNo (String personalNo) {
        this.personalNo = personalNo;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode () {
        return nationalCode;
    }

    public void setNationalCode (String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
