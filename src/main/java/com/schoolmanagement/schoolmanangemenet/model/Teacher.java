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
    @ManyToMany (fetch = FetchType.LAZY)
    @JoinTable (name = "teacher_students", joinColumns = @JoinColumn (name = "student_no"), inverseJoinColumns =
    @JoinColumn (name = "teacher_id"))
    private List<Student> students;

    public String getPersonalNo () {
        return personalNo;
    }

    public void setPersonalNo (String personalNo) {
        this.personalNo = personalNo;
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

    public List<Lesson> getLessons () {
        return lessons;
    }

    public void setLessons (List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Student> getStudents () {
        return students;
    }

    public void setStudents (List<Student> students) {
        this.students = students;
    }
}
