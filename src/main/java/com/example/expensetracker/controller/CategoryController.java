package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
       this.categoryService = categoryService;
    }

    @GetMapping("")
    public ModelAndView home(){
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("data", categoryService.getCategories());
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
    public String submitCreate(Category category, Model model){
        boolean success = categoryService.save(category);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/categories";
    }
}
