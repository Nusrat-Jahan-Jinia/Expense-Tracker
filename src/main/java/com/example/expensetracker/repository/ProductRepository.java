package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RepositoryRestResource(collectionResourceRel = "productList", path = "productList")
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
