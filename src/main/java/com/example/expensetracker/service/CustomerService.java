package com.example.expensetracker.service;

import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    public boolean save(Customer customer) throws DataAccessException {
        customerRepository.save(customer);
        return true;
    }

    public Customer findById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.orElse(null); // or throw an exception if needed
    }
}
