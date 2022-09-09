package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Student {
    @Id
    @Column (unique = true, nullable = false, length = 7)
    private String studentNo;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column (unique = true, nullable = false, length = 10)
    private String nationalCode;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Lesson> lessons;
    @ManyToMany (mappedBy = "students")
    private List<Teacher> teachers;
}
