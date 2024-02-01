package com.example.expensetracker.service;

import com.example.expensetracker.entity.CustomerOrder;
import com.example.expensetracker.exception.CustomerOrderUpdateException;
import com.example.expensetracker.repository.CustomerOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private final CustomerOrderRepository customerOrderRepository;
    public OrderService(CustomerOrderRepository customerOrderRepository){
        this.customerOrderRepository = customerOrderRepository;
    }

    public CustomerOrder getOrderById(int id) {
        return customerOrderRepository.findById(id).orElse(null);
    }

    public void edit(CustomerOrder updatedCustomerOrder, int id) {
        Optional<CustomerOrder> existingOrderOptional = customerOrderRepository.findById(id);

        if (existingOrderOptional.isPresent()) {
            CustomerOrder existingOrder = existingOrderOptional.get();
            existingOrder.setOrderBy(updatedCustomerOrder.getOrderBy());
            existingOrder.setOrderDate(updatedCustomerOrder.getOrderDate());
            existingOrder.setCustomer(updatedCustomerOrder.getCustomer());
            existingOrder.setProducts(updatedCustomerOrder.getProducts());

            customerOrderRepository.save(existingOrder);
        } else {
            throw new CustomerOrderUpdateException("Customer order with id " + id + " not found");
        }
    }
}
