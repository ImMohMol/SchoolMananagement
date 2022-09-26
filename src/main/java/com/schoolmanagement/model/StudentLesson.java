package com.schoolmanagement.model;

import javax.persistence.*;

@Entity
public class StudentLesson {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    private Student student;
    @ManyToOne (fetch = FetchType.LAZY)
    private Lesson lesson;
    @Column (nullable = false, scale = 2, precision = 3)
    private double score;

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public Student getStudent () {
        return student;
    }

    public void setStudent (Student student) {
        this.student = student;
    }

    public Lesson getLesson () {
        return lesson;
    }

    public void setLesson (Lesson lesson) {
        this.lesson = lesson;
    }

    public Double getScore () {
        return score;
    }

    public void setScore (Double score) {
        this.score = score;
    }
}
