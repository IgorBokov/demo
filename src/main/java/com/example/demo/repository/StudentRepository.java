package com.example.demo.repository;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
// CRUD - Create, Read, Update, Delete
public interface StudentRepository extends CrudRepository<Student, Long> {} /*из models -> класс Student,
                                                                            тип идентификатора у поля ID
                                                                            в классе Student -> Long*/

