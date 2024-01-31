package com.example.expensetracker.service;

import com.example.expensetracker.entity.CustomerOrder;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final CustomerOrderRepository customerOrderRepository;
    public OrderService(CustomerOrderRepository customerOrderRepository){
        this.customerOrderRepository = customerOrderRepository;
    }

    public CustomerOrder getOrderById(int id) {
        return customerOrderRepository.findById(id).orElse(null);
    }

    public boolean edit(CustomerOrder customerOrder, int id) {
        try {
            customerOrder.setId(id);
            customerOrderRepository.save(customerOrder);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
