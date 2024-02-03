package com.example.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HomeService {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    public HomeService(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    public BigDecimal getTotalExpenseAfterLastMonth() {
        BigDecimal totalExpense = expenseService.getTotalAmountAfterLastMonth();
        return totalExpense;
    }
    public BigDecimal getTotalIncomeAfterLastMonth() {
        BigDecimal totalIncome = incomeService.getTotalAmountAfterLastMonth();
        return totalIncome;
    }
    public BigDecimal getTotalSavingsAfterLastMonth() {
        BigDecimal totalExpense = expenseService.getTotalAmountAfterLastMonth();
        BigDecimal totalIncome = incomeService.getTotalAmountAfterLastMonth();
        BigDecimal totalSavings = totalIncome.subtract(totalExpense);
        return totalSavings;
    }
}
