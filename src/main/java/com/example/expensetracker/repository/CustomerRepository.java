package com.example.expensetracker.repository;

import com.example.expensetracker.dto.OrderResponse;
import com.example.expensetracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT new com.example.expensetracker.dto.OrderResponse(c.name, p.name) FROM Customer c JOIN c.products p")
    public List<OrderResponse> getRestInformation();

    @Query("SELECT new com.example.expensetracker.dto.OrderResponse(c.name, c.gender, c.email, c.id, p.name, p.price, p.qty) FROM Customer c JOIN c.products p")
    public List<OrderResponse> getViewInformation();

}
