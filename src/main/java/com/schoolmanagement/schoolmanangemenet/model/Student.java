package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @Column (unique = true, nullable = false, length = 7, columnDefinition = "This is the student number in university" +
            " that is a unique number")
    private String studentNo;
    @Column (nullable = false, columnDefinition = "This is the student name")
    private String firstName;
    @Column (nullable = false, columnDefinition = "This is the student last name")
    private String lastName;
    @Column (unique = true, nullable = false, length = 10, columnDefinition = "This is the student national code that " +
            "must be unique")
    private String nationalCode;
}
