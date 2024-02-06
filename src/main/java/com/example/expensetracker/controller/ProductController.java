package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Product;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController implements WebMvcConfigurer {
    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/product/create/results").setViewName("product/results");
    }

    @GetMapping(value = "")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping(value = "/create")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping(value = "/create")
    public String addProduct(Product product) {
        productRepository.save(product);
        return "redirect:/product/create/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "product/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product, Model model) {
        productRepository.save(product);
        return "redirect:/products";
    }
}
