package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findByDateAfter(LocalDate lastMonthEndDate);
}
