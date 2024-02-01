package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }

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

    public void deleteById(int id) {
        expenseRepository.deleteById(id);
    }

    public double getTotalAmountAfterLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonthEndDate = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        List<Expense> expensesAfterLastMonth = expenseRepository.findByDateAfter(lastMonthEndDate);
        return expensesAfterLastMonth.stream().mapToDouble(Expense::getAmount).sum();
    }
}
