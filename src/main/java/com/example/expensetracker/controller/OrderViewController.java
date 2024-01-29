package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.entity.Expense;
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
@RequestMapping("orders")
public class OrderViewController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", customerRepository.findAll());
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

    @GetMapping(value = "/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dto", new Customer());
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("order/create.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(@ModelAttribute("dto")Customer customer, Model model){
        boolean success = customerService.save(customer);

        if(!success){
            model.addAttribute("result", "Something went wrong!");
        }else{
            model.addAttribute("result", "Successfully data entered!");
        }
        return "redirect:/orders";
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id", id);
        modelAndView.addObject("dto", customerRepository.findById(id));
        modelAndView.addObject("method", "put");
        System.out.println(customerRepository.findById(id));
        modelAndView.setViewName("order/edit.html");
        return modelAndView;
    }

    @PutMapping(value = "/{id}")
    public ModelAndView submitUpdate(@PathVariable("id") int id, Customer customer, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        customerRepository.save(customer);
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }
}
