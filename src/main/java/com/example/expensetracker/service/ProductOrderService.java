package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.ProductOrder;
import com.example.expensetracker.exception.CustomerOrderUpdateException;
import com.example.expensetracker.repository.ProductOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductOrderService {
    private final ProductOrderRepository productOrderRepository;
    public ProductOrderService(ProductOrderRepository productOrderRepository){
        this.productOrderRepository = productOrderRepository;
    }

    public List<ProductOrder> getOrders(){
        return productOrderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        productOrderRepository.deleteById(id);
    }

    public Optional<ProductOrder> getOrderById(Long id) {
        return productOrderRepository.findById(id);
    }

//    public void edit(ProductOrder updatedCustomerOrder, int id) {
//        Optional<ProductOrder> existingOrderOptional = productOrderRepository.findById(id);
//
//        if (existingOrderOptional.isPresent()) {
//            ProductOrder existingOrder = existingOrderOptional.get();
//            existingOrder.setOrderBy(updatedCustomerOrder.getOrderBy());
//            existingOrder.setOrderDate(updatedCustomerOrder.getOrderDate());
//            existingOrder.setCustomer(updatedCustomerOrder.getCustomer());
//            existingOrder.setProducts(updatedCustomerOrder.getProducts());
//
//            productOrderRepository.save(existingOrder);
//        } else {
//            throw new CustomerOrderUpdateException("Customer order with id " + id + " not found");
//        }
//    }
}
