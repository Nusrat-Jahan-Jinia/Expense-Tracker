package com.example.expensetracker.repository;

import com.example.expensetracker.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(collectionResourceRel = "productOrderList", path = "productOrderList")
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {
}
