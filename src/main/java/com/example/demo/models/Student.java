package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*автогенерация ID*/
    private Long id;
    private String lastname;
    private String name;
    private String stream_group;
    private boolean status;
    private Date date;
    public Student(){}
    public Student(String lastname, String name, String stream_group, boolean status, Date date) {
        this.lastname = lastname;
        this.name = name;
        this.stream_group = stream_group;
        this.status = status;
        this.date = date;
    }
}
