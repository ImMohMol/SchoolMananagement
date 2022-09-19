package com.schoolmanagement.schoolmanangemenet.model.dto.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateStudentDTO {
    @NotNull (message = "StudentNo is needed to update the student information!")
    @Size (max = 7, min = 7, message = "The studentNo must be 7 digits!")
    private String studentNo;
    @NotNull (message = "The student first name can't be null!")
    private String firstName;
    @NotNull (message = "The student last name can't be null!")
    private String lastName;
    @NotNull (message = "The student national code can't be null!")
    @Size (max = 10, min = 10, message = "The student national code must be 10 digits!")
    private String nationalCoe;

    public String getStudentNo () {
        return studentNo;
    }

    public void setStudentNo (String studentNo) {
        this.studentNo = studentNo;
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

    public String getNationalCoe () {
        return nationalCoe;
    }

    public void setNationalCoe (String nationalCoe) {
        this.nationalCoe = nationalCoe;
    }
}
