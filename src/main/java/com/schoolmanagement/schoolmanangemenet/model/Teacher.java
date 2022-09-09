package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Teacher {
    @Id
    @Column (unique = true, nullable = false)
    private String personalNo;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column (unique = true, nullable = false, length = 10)
    private String nationalCode;
    @ManyToMany (mappedBy = "teachers", fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
