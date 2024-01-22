package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getExpenses(){
        return (List<Expense>) expenseRepository.findAll();
    }

    public boolean save(Expense expense) throws DataAccessException {
        expenseRepository.save(expense);
        return true;
    }

    public Expense getExpenseById(int id) {
        return expenseRepository.findById(id).orElse(null);
    }
    public void updateExpense(Expense expense) {
        if (expense.getId() != null && expenseRepository.existsById(expense.getId())) {
            expenseRepository.save(expense);
        }
    }
    public boolean edit(Expense expense, int id) {

        try {
            expense.setId(id);
            expenseRepository.save(expense);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
