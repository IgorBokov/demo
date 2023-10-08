package com.example.demo.controllers;

import com.example.demo.models.BindStudentDiscipline;
import com.example.demo.models.Discipline;
import com.example.demo.models.Student;
import com.example.demo.repository.BindStudentDisciplineRepository;
import com.example.demo.repository.DisciplineRepository;
import com.example.demo.repository.StudentRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Validated
@Controller
public class StudentController {
    @Autowired                                   /*авто подключение*/
    private StudentRepository studentRepository; /*подключили интерфейс StudentRepository и у studentRepository
                                                  есть функции CRUD - Create, Read, Update, Delete*/
    @Autowired
    private DisciplineRepository disciplineRepository;
    @Autowired
    private BindStudentDisciplineRepository bindStudentDisciplineRepository;

    @GetMapping("/student/{id}")
    public String showStudent(@PathVariable(value = "id") long id, Model model) {
        Optional<Student> student = studentRepository.findById(id);
        model.addAttribute("student", student.get());              //student.get()
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        model.addAttribute("disciplines", disciplines);
        return "student";
    }

    @PostMapping("/student")
    public String studentPerfomance(
            @RequestParam long id,
            @RequestParam(value = "selectedOptions", required = false) List<Long> selectedOptions
    ) {
        if (selectedOptions != null) {
            for (Long option : selectedOptions) {
                System.out.println(option);
            }
        }
        return "redirect:/student";
    }

    @GetMapping("/addStudent")
    public String addStudent() {
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String saveStudent(
            @RequestParam @NotBlank String lastname,   /*принимаем данные из <form action="/addStudent" method="post">*/
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String stream_group,
            @RequestParam @NotNull Date date
    ) {
        Student student = new Student(lastname, name, stream_group, true, date);
        studentRepository.save(student);       /*сохраняем полученные данные в DB*/
        return "redirect:/addStudent";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students); /*записываем в model -> ключ(students),значение(students)
        полученные данные передаем в шаблон (students.html) строка -> <tr th:each="el : ${students}">*/
        return "students";
    }

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable(value = "id") long id, Model model) {
        Optional<Student> student = studentRepository.findById(id);
        model.addAttribute("student", student.get()); //student.get()
        return "editStudent";
    }

    @PostMapping("/editStudent")
    public String modifyStudent(
            @RequestParam long id,
            @RequestParam @NotBlank String lastname,   /*принимаем данные из <form action="/addStudent" method="post">*/
            @RequestParam @NotBlank String name,
            @RequestParam @NotBlank String stream_group,
            @RequestParam @NotNull Date date
    ) {
        Optional<Student> student = studentRepository.findById(id);
        Student student1 = student.get();   //сохранили все полученные данные из БД таб student в student1
        student1.name = name;
        student1.lastname = lastname;
        student1.stream_group = stream_group;
        student1.date = date;
        studentRepository.save(student1);       /*сохраняем полученные данные в DB*/
        return "redirect:/students";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") long id) {
        studentRepository.deleteById(id);       /* удаление данных из DB*/
        return "redirect:/students";
    }

}
