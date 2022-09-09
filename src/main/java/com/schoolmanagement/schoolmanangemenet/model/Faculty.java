package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Faculty {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true, nullable = false)
    private String name;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Teacher> teachers;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Student> students;
    @OneToMany (fetch = FetchType.LAZY)
    private List<Lesson> lessons;
}
