package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String discipline;
    public boolean status;

    public Discipline() {
    }

    public Discipline(String discipline, boolean status) {
        this.discipline = discipline;
        this.status = status;
    }
}