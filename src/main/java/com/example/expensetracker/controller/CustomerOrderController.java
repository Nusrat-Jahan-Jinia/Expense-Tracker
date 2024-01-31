package com.example.expensetracker.controller;

import com.example.expensetracker.entity.*;
import com.example.expensetracker.repository.CustomerOrderRepository;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class CustomerOrderController {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CustomerOrderRepository customerOrderRepository;
    private final OrderService orderService;

    @Autowired
    public CustomerOrderController(
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            CustomerOrderRepository customerOrderRepository,
            OrderService orderService
    ) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerOrderRepository = customerOrderRepository;
        this.orderService = orderService;
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("data", customerOrderRepository.findAll());
//        System.out.println(customerOrderRepository.findAll());
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        modelAndView.addObject("dto", new CustomerOrder());
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("products", products);
        modelAndView.addObject("method", "post");
        modelAndView.setViewName("order/create.html");
        return modelAndView;
    }

    @PostMapping(value = "")
    public String submitCreate(CustomerOrder customerOrder, Model model){
        customerOrderRepository.save(customerOrder);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{id}")
    public String deleteEntity(@PathVariable int id) {
        customerOrderRepository.deleteById(id);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/update")
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        modelAndView.addObject("id", id);
        modelAndView.addObject("dto", orderService.getOrderById(id));

        modelAndView.addObject("customers", customers);
        modelAndView.addObject("products", products);
        modelAndView.addObject("method", "put");
        modelAndView.setViewName("order/edit.html");
        return modelAndView;
    }

    @PutMapping(value = "/{id}")
    public ModelAndView submitUpdate(@PathVariable("id") int id, CustomerOrder customerOrder, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        boolean success = orderService.edit(customerOrder, id);
        if (!success) {
            model.addAttribute("result", "Something went wrong!");
        } else {
            model.addAttribute("result", "Successfully data updated!");
        }
        modelAndView.setViewName("order/list.html");
        return modelAndView;
    }

}
