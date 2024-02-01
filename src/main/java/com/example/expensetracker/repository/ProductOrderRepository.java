package com.example.expensetracker.repository;

import com.example.expensetracker.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
}
