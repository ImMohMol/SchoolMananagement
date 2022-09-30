package com.schoolmanagement.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column (unique = true, nullable = false, length = 7)
    private String studentNo;
    @Column (nullable = false)
    private String firstName;
    @Column (nullable = false)
    private String lastName;
    @Column (unique = true, nullable = false, length = 10)
    private String nationalCode;
    @OneToMany (fetch = FetchType.LAZY)
    private List<StudentLesson> lessons;
    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "students")
    private List<Teacher> teachers;

    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public String getStudentNo () {
        return studentNo;
    }

    public void setStudentNo (String studentNo) {
        this.studentNo = studentNo;
    }

    public String getFirstName () {
        return firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }

    public String getLastName () {
        return lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }

    public String getNationalCode () {
        return nationalCode;
    }

    public void setNationalCode (String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public List<StudentLesson> getLessons () {
        return lessons;
    }

    public void setLessons (List<StudentLesson> lessons) {
        this.lessons = lessons;
    }

    public List<Teacher> getTeachers () {
        return teachers;
    }

    public void setTeachers (List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return getStudentNo().equals(student.getStudentNo());
    }

    @Override
    public int hashCode () {
        return Objects.hash(getStudentNo());
    }
}
