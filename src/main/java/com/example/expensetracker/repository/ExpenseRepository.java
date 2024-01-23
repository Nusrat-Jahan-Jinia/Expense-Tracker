package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Transactional
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Expense> findByDateAfter(LocalDate lastMonthEndDate);
}
