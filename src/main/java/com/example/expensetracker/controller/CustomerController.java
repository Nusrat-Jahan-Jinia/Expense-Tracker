package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
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
        return "redirect:/customers";
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

    @PostMapping("/edit/{id}")
    public String updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

}
