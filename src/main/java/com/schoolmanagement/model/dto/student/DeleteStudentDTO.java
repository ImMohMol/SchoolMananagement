package com.schoolmanagement.model.dto.student;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeleteStudentDTO {
    @NotNull (message = "StudentNo is needed to update the student information!")
    @Size (max = 7, min = 7, message = "The studentNo must be 7 digits!")
    private String studentNo;

    public String getStudentNo () {
        return studentNo;
    }

    public void setStudentNo (String studentNo) {
        this.studentNo = studentNo;
    }
}
