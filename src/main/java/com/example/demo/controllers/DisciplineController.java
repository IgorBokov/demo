package com.example.demo.controllers;

import com.example.demo.models.Discipline;
import com.example.demo.repository.DisciplineRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Validated
@Controller
public class DisciplineController {
    @Autowired
    private DisciplineRepository disciplineRepository;

    @GetMapping("/disciplines")
    public String showDisciplines(Model model) {
        Iterable<Discipline> disciplines = disciplineRepository.findAll();
        model.addAttribute("disciplines", disciplines);/*записываем в model -> ключ(disciplines),значение(disciplines)
        полученные данные передаем в шаблон (disciplines.html) строка -> <tr th:each="el : ${disciplines}">*/
        return "disciplines";
    }

    @PostMapping("/addDiscipline")
    public String showAddDiscipline(
            @RequestParam String discipline   /*принимаем данные из
              <input name="discipline" type="text" class="form-control" placeholder="Наименование дисциплины">*/
    ) {

        Discipline disciplin = new Discipline(discipline, true);
        disciplineRepository.save(disciplin);       /*сохраняем полученные данные в DB*/
        return "redirect:/disciplines";
    }

    @GetMapping("/addDiscipline")
    public String addDiscipline() {
        return "addDiscipline";
    }

    @GetMapping("/editDiscipline/{id}")
    public String showEditDiscipline(@PathVariable(value = "id") long id, Model model) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        model.addAttribute("discipline", discipline.get());

        return "editDiscipline";
    }

    @PostMapping("/editDiscipline")
    public String editDiscipline(
            @RequestParam long id,
            @RequestParam @NotBlank String discipline   /*принимаем данные из <form action="/addDiscipline" method="post">*/
    ) {
        Optional<Discipline> disciplin = disciplineRepository.findById(id);
        Discipline discipl = disciplin.get();
        discipl.discipline = discipline;
        disciplineRepository.save(discipl);       /*сохраняем полученные данные в DB*/

        return "redirect:/disciplines";
    }

    @GetMapping("/deleteDiscipline/{id}")
    public String deleteDiscipline(@PathVariable(value = "id") long id) {
        disciplineRepository.deleteById(id);       /* удаление данных из DB*/
        return "redirect:/disciplines";
    }
}