package com.example.expensetracker.service;

import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
