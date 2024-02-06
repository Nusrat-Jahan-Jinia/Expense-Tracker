package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@RepositoryRestResource(collectionResourceRel = "incomeList", path = "incomeList")
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findByDateAfter(LocalDate lastMonthEndDate);
}
