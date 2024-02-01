package com.example.expensetracker.repository;

import com.example.expensetracker.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
}
