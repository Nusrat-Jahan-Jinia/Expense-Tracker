package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.entity.Product;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.CustomerService;
import com.example.expensetracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderViewController {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderViewController(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

//    @GetMapping("/create")
//    public ModelAndView create(){
//        ModelAndView modelAndView = new ModelAndView();
//        List<Customer> customers = customerRepository.findAll();
//        List<Product> products = productRepository.findAll();
//        modelAndView.addObject("customers", customers);
//        modelAndView.addObject("products", products);
//        modelAndView.addObject("method", "post");
//        modelAndView.setViewName("order/list.html");
//        return modelAndView;
//    }



}
