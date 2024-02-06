package com.example.expensetracker.service;

import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
}
