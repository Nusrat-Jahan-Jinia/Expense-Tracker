package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.service.CategoryService;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController implements WebMvcConfigurer {
    private final ExpenseService expenseService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public ExpenseController(
            ExpenseService expenseService,
            CategoryService categoryService,
            CategoryRepository categoryRepository,
            ExpenseRepository expenseRepository
    ){
        this.expenseService = expenseService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/expense/create/results").setViewName("expense/results");
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
        return "redirect:/expense/create/results";
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

    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id, @ModelAttribute("category") Expense expense, Model model) {
        expenseRepository.save(expense);
        return "redirect:/expense/create/results";
    }

}
