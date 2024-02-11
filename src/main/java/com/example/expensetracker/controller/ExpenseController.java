package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.CategoryService;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    private final CategoryService categoryService;
    private final ExpenseRepository expenseRepository;

    public ExpenseController(
            ExpenseService expenseService,
            CategoryService categoryService,
            ExpenseRepository expenseRepository
    ){
        this.expenseService = expenseService;
        this.categoryService = categoryService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping(value = "")
    public String getAllExpenses(Model model) {
        List<Expense> expenses = expenseService.getExpenses();
        model.addAttribute("expenses", expenses);
        return "expense/list";
    }

    @GetMapping(value = "/create")
    public String showAddExpenseForm(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("expense", new Expense());
        model.addAttribute("categories", categories);
        return "expense/create";
    }

    @PostMapping(value = "/create")
    public String addExpense(Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/expenses";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses";
    }

    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        List<Category> categories = categoryService.getCategories();
        Expense expense = expenseService.getExpenseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        model.addAttribute("expense", expense);
        model.addAttribute("categories", categories);
        return "expense/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateExpense(@ModelAttribute("category") Expense expense) {
        expenseRepository.save(expense);
        return "redirect:/expenses";
    }

}
