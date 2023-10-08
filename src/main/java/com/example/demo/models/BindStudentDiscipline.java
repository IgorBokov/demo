package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class BindStudentDiscipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long student_id;
    public Long discipline_id;

    public BindStudentDiscipline() {
    }

    public BindStudentDiscipline(Long student_id, Long discipline_id) {
        this.student_id = student_id;
        this.discipline_id = discipline_id;
    }

}
