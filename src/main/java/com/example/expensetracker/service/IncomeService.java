package com.example.expensetracker.service;

import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    public IncomeService(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

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
//    public void updateExpense(Income income) {
//        if (income.getId() != null && incomeRepository.existsById(income.getId())) {
//            incomeRepository.save(income);
//        }
//    }
    public boolean edit(Income income) {
        incomeRepository.save(income);
        return true;
    }
    public void deleteById(int id) {
        incomeRepository.deleteById(id);
    }

    public BigDecimal getTotalAmountAfterLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonthEndDate = today.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        List<Income> incomesAfterLastMonth = incomeRepository.findByDateAfter(lastMonthEndDate);
        // Initialize BigDecimal sum to zero
        BigDecimal totalAmount = BigDecimal.ZERO;

        // Iterate through incomesAfterLastMonth and sum amounts
        for (Income income : incomesAfterLastMonth) {
            BigDecimal amount = income.getAmount();
            totalAmount = totalAmount.add(amount);
        }
        return totalAmount;

    }
}
