package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Income;
import com.example.expensetracker.entity.Tag;
import com.example.expensetracker.service.CategoryService;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.IncomeService;
import com.example.expensetracker.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/incomes")
public class IncomeController {

    private final IncomeService incomeService;
    private final TagService tagService;

    @Autowired
    public IncomeController(IncomeService incomeService, TagService tagService){
        this.incomeService = incomeService;
        this.tagService = tagService;
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", incomeService.getIncomes());
        modelAndView.setViewName("income/list.html");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        List<Tag> tags = tagService.getTags();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Income());
        modelAndView.addObject("tags", tags);
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("income/create.html");
        return modelAndView;
    }
    @PostMapping(value = "")
    public String submitCreate(@Validated @ModelAttribute("income") Income income, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // Handle validation errors, e.g., return to the form page with error messages
            return "income/create.html";
        }

        boolean success = incomeService.save(income);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/incomes";
    }
    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.addObject("dto", incomeService.getIncomeById(id));
        List<Tag> tags = tagService.getTags();
        modelAndView.addObject("tags", tags);
        modelAndView.addObject("method", "put");
        modelAndView.setViewName("income/edit.html");
        return modelAndView;
    }

    @PutMapping(value = "/{id}")
    public ModelAndView submitUpdate(@PathVariable("id") int id, Income income, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        boolean success = incomeService.edit(income, id);
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
        incomeService.deleteById(id);
        return "redirect:/incomes";
    }
}
