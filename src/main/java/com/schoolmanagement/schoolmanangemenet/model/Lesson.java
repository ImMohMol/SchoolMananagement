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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (name = "lesson_teachers", joinColumns = @JoinColumn (name = "teacher_id"),
            inverseJoinColumns = @JoinColumn (name = "lesson_id"))
    private List<Teacher> teachers;
}
