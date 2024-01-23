package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    public List<Income> getIncomes(){
        return (List<Income>) incomeRepository.findAll();
    }

    public boolean save(Income income) throws DataAccessException {
        incomeRepository.save(income);
        return true;
    }

    public Income getIncomeById(int id) {
        return incomeRepository.findById(id).orElse(null);
    }
    public void updateExpense(Income income) {
        if (income.getId() != null && incomeRepository.existsById(income.getId())) {
            incomeRepository.save(income);
        }
    }
    public boolean edit(Income income, int id) {
        try {
            income.setId(id);
            incomeRepository.save(income);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    public void deleteById(int id) {
        incomeRepository.deleteById(id);
    }

    public double getTotalAmountAfterLastMonth() {
        // Calculate last month's end date
        LocalDate today = LocalDate.now();
        LocalDate lastMonthEndDate = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

        // Retrieve expenses after last month
        List<Income> expensesAfterLastMonth = incomeRepository.findByDateAfter(lastMonthEndDate);

        // Calculate total amount
        return expensesAfterLastMonth.stream().mapToDouble(Income::getAmount).sum();
    }
}
