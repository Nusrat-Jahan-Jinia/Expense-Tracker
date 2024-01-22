package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
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

    public void deleteById(int id) {
        expenseRepository.deleteById(id);
    }

    public long getLastMonthExpenseCount() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date lastMonthStartDate = getStartOfMonth(calendar.getTime());
        Date lastMonthEndDate = getEndOfMonth(calendar.getTime());

        List<Expense> lastMonthExpenses = expenseRepository.findByDateBetween(lastMonthStartDate, lastMonthEndDate);
        return lastMonthExpenses.size();
    }

    private Date getStartOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private Date getEndOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
}
