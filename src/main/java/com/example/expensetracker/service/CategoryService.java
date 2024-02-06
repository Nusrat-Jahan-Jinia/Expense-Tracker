package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.repository.CategoryRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public CategoryService(
            CategoryRepository categoryRepository
    ){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories(){
        return (List<Category>) categoryRepository.findAll();
    }

    public boolean save(Category category) throws DataAccessException {
        categoryRepository.save(category);
        return true;
    }
}
