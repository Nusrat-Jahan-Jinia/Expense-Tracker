package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;



@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository){
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = "")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping(value = "/create")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping(value = "/create")
    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute("category") Category category, Model model) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }

}

