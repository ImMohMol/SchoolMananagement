package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lesson {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (unique = true, nullable = false)
    private String name;
    @Column (nullable = false)
    private Integer gradeNumber;
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "lesson_teachers", joinColumns = @JoinColumn (name = "teacher_id"),
            inverseJoinColumns = @JoinColumn (name = "lesson_id"))
    private List<Teacher> teachers;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public Integer getGradeNumber () {
        return gradeNumber;
    }

    public void setGradeNumber (Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public List<Teacher> getTeachers () {
        return teachers;
    }

    public void setTeachers (List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
