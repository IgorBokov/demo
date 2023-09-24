package com.example.demo.controllers;

import com.example.demo.models.Student;
import com.example.demo.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class StudentController {
    @Autowired                         /*авто подключение*/
    private StudentRepo studentRepo;   /*подключили интерфейс StudentRepo и у studentRepo есть функции
                                       CRUD - Create, Read, Update, Delete*/

    @GetMapping("/addStudent")
    public String addStudent() {
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String saveStudent(
            @RequestParam String lastname,   /*принимаем данные из <form action="/addStudent" method="post">*/
            @RequestParam String name,
            @RequestParam String stream_group,
            @RequestParam Date date
    ) {
        Student student = new Student(lastname, name, stream_group, true, date);
        studentRepo.save(student);       /*сохраняем полученные данные в DB*/
        return "redirect:/addStudent";
    }
}
