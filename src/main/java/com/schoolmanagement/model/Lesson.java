package com.schoolmanagement.model;

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
    private int gradeNumber;
    @OneToMany (fetch = FetchType.LAZY)
    private List<StudentLesson> students;
    @ManyToMany (fetch = FetchType.LAZY)
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

    public int getGradeNumber () {
        return gradeNumber;
    }

    public void setGradeNumber (int gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public List<StudentLesson> getStudents () {
        return students;
    }

    public void setStudents (List<StudentLesson> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers () {
        return teachers;
    }

    public void setTeachers (List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
