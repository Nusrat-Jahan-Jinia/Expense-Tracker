package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Product;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
