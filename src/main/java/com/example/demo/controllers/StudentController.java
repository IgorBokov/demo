package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class StudentController {
    @GetMapping("/addStudent")
    public String addStudent(){
        return "addStudent";
    }
}
