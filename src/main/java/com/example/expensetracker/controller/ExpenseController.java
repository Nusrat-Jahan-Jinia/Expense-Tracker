package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.service.CategoryService;
import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", expenseService.getExpenses());
        modelAndView.setViewName("expense/list.html");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        List<Category> categories = categoryService.getCategories();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Expense());
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("expense/create.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(@ModelAttribute("dto") Expense expense, Model model){
        boolean success = expenseService.save(expense);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/expenses";
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.addObject("dto", expenseService.getExpenseById(id));
        List<Category> categories = categoryService.getCategories();
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("method", "put");
        modelAndView.setViewName("expense/edit.html");
        return modelAndView;
    }

    @PutMapping(value = "/{id}")
    public ModelAndView submitUpdate(@PathVariable("id") int id, Expense expense, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        boolean success = expenseService.edit(expense, id);
        if (!success) {
            model.addAttribute("result", "Something went wrong!");
        } else {
            model.addAttribute("result", "Successfully data updated!");
        }
        modelAndView.setViewName("expense/list.html");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteEntity(@PathVariable int id) {
        expenseService.deleteById(id);
        return "redirect:/expenses";
    }

}
