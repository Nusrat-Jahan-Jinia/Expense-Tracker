package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.entity.Product;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", productRepository.findAll());
        modelAndView.setViewName("product/list.html");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Product());
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("product/create.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(@ModelAttribute("dto")Product product, Model model){
        productRepository.save(product);
        return "redirect:/products";
    }
}
