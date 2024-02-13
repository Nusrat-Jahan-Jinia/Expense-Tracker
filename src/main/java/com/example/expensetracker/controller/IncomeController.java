package com.example.expensetracker.controller;


import com.example.expensetracker.entity.Income;
import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.repository.IncomeRepository;
import com.example.expensetracker.service.IncomeService;
import com.example.expensetracker.service.TagService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    private final TagService tagService;
    private final IncomeRepository incomeRepository;

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
    public String addIncome(@Valid Income income, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Tag> tags = tagService.getTags();
            model.addAttribute("tags", tags);
            return "income/create";
        }
        incomeRepository.save(income);
        return "redirect:/incomes";
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

    @PostMapping("/edit/{id}")
    public String updateIncome(@Valid @ModelAttribute("income") Income income, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            List<Tag> tags = tagService.getTags();
            model.addAttribute("tags", tags);
            return "income/create";
        }
        incomeRepository.save(income);
        return "redirect:/incomes";
    }
}
