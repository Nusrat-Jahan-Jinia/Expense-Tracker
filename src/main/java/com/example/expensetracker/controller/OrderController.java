package com.example.expensetracker.controller;

import com.example.expensetracker.dto.OrderRequest;
import com.example.expensetracker.dto.OrderResponse;
import com.example.expensetracker.entity.Customer;
import com.example.expensetracker.repository.CustomerRepository;
import com.example.expensetracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request){
        return customerRepository.save(request.getCustomer());
    }

    @GetMapping("/findAllOrders")
    public List<Customer> findAllOrders(){
        return customerRepository.findAll();
    }
    @GetMapping("/getInformation")
    public List<OrderResponse> getJoinInformation(){
        return customerRepository.getRestInformation();
    }
}
