package com.schoolmanagement.model;

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

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<Teacher> getTeachers () {
        return teachers;
    }

    public void setTeachers (List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents () {
        return students;
    }

    public void setStudents (List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons () {
        return lessons;
    }

    public void setLessons (List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
