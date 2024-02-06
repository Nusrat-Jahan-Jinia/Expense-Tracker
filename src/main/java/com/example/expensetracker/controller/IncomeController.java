package com.example.expensetracker.controller;


import com.example.expensetracker.entity.Income;
import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.service.IncomeService;
import com.example.expensetracker.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("/incomes")
public class IncomeController implements WebMvcConfigurer {

    private final IncomeService incomeService;
    private final TagService tagService;
    private final IncomeRepository incomeRepository;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/income/create/results").setViewName("income/results");
    }
    public IncomeController(IncomeService incomeService, TagService tagService,IncomeRepository incomeRepository){
        this.incomeService = incomeService;
        this.tagService = tagService;
        this.incomeRepository = incomeRepository;
    }

    @GetMapping(value = "")
    public String getAllIncomes(Model model) {
        List<Income> incomes = incomeService.getIncomes();
        model.addAttribute("incomes", incomes);
        return "income/list";
    }

    @GetMapping(value = "/create")
    public String showAddIncomeForm(Model model) {
        List<Tag> tags = tagService.getTags();
        model.addAttribute("income", new Income());
        model.addAttribute("tags", tags);
        return "income/create";
    }

    @PostMapping(value = "/create")
    public String addIncome(Income income) {
        incomeRepository.save(income);
        return "redirect:/income/create/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return "redirect:/incomes";
    }

    @GetMapping("/edit/{id}")
    public String showEditIncomeForm(@PathVariable Long id, Model model) {
        List<Tag> tags = tagService.getTags();
        Income income = incomeService.getIncomeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid income Id:" + id));
        model.addAttribute("income", income);
        model.addAttribute("tags", tags);
        return "income/edit";
    }

    @PostMapping("/update/{id}")
    public String updateIncome(@PathVariable Long id, @ModelAttribute("income") Income income, Model model) {
        incomeRepository.save(income);
        return "redirect:/incomes";
    }
}
