package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.service.CategoryService;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


@Controller
@Validated
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public ModelAndView home(){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("data", categoryRepository.findAll());
      modelAndView.setViewName("category/list.html");
      return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Category());
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("category/create-edit.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(@RequestBody @Validated @ModelAttribute("category") Category category, Model model, BindingResult bindingResult){


        if (bindingResult.hasErrors()) {
            System.out.println("error here");
            return "category/create-edit.html";
        }

        boolean success = categoryService.save(category);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/categories";
    }


}
