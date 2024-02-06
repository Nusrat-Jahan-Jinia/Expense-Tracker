package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RepositoryRestResource(collectionResourceRel = "customerList", path = "customerList")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
