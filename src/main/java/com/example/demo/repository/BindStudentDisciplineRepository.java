package com.example.demo.repository;

import com.example.demo.models.BindStudentDiscipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BindStudentDisciplineRepository extends CrudRepository<BindStudentDiscipline,Long> {}
