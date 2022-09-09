package com.schoolmanagement.schoolmanangemenet.model;

import javax.persistence.*;

@Entity
public class Faculty {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column (unique = true, nullable = false)
    private String name;
}
