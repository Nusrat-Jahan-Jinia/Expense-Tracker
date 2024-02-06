package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController implements WebMvcConfigurer {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, ProductRepository productRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.customerService = customerService;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/customer/create/results").setViewName("customer/results");
    }
    @GetMapping(value = "")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping(value = "/create")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping(value = "/create")
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customer/create/results";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @ModelAttribute("customer") Customer customer, Model model) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

}
