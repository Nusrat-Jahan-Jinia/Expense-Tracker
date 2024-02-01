package com.example.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @Autowired
    public HomeService(ExpenseService expenseService, IncomeService incomeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    public double getTotalExpenseAfterLastMonth() {
        double totalExpense = expenseService.getTotalAmountAfterLastMonth();
        return totalExpense;
    }
    public double getTotalIncomeAfterLastMonth() {
        double totalIncome = incomeService.getTotalAmountAfterLastMonth();
        return totalIncome;
    }
    public double getTotalSavingsAfterLastMonth() {
        double totalExpense = expenseService.getTotalAmountAfterLastMonth();
        double totalIncome = incomeService.getTotalAmountAfterLastMonth();
        double totalSavings = totalIncome - totalExpense;
        return totalSavings;
    }
}
