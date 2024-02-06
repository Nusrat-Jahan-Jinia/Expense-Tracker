package com.example.expensetracker.service;

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
        return expenseService.getTotalAmountAfterLastMonth();
    }
    public BigDecimal getTotalIncomeAfterLastMonth() {
        return incomeService.getTotalAmountAfterLastMonth();
    }
    public BigDecimal getTotalSavingsAfterLastMonth() {
        BigDecimal totalExpense = expenseService.getTotalAmountAfterLastMonth();
        BigDecimal totalIncome = incomeService.getTotalAmountAfterLastMonth();
        return totalIncome.subtract(totalExpense);
    }
}
