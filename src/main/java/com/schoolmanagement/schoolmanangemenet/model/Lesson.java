package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true, nullable = false)
    private String name;
    @ManyToMany
    @JoinTable (name = "lesson_teacher", joinColumns = @JoinColumn (name = "teacher_id", referencedColumnName =
            "personal_no"), inverseJoinColumns = @JoinColumn (name = "lesson_id", referencedColumnName = "id"))
    private List<Teacher> teachers;
}
