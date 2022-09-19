package com.schoolmanagement.schoolmanangemenet.model.dto.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateStudentDTO {
    @NotNull (message = "The student first name can't be null!")
    private String firstName;
    @NotNull (message = "The student last name can't be null!")
    private String lastName;
    @NotNull (message = "The student national code can't be null!")
    @Size (max = 10, min = 10, message = "The student national code must be 10 digits!")
    private String nationalCode;

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
