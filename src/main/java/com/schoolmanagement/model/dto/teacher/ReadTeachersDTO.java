package com.schoolmanagement.model.dto.teacher;

public class ReadTeachersDTO {
    private String personalNo;
    private String firstName;
    private String lastName;
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
