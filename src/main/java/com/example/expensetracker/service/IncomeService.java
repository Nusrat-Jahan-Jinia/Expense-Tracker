package com.example.expensetracker.service;

import com.example.expensetracker.entity.Category;
import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeService {
    private final IncomeRepository incomeRepository;
    public IncomeService(IncomeRepository incomeRepository){
        this.incomeRepository = incomeRepository;
    }

    public List<Income> getIncomes(){
        return incomeRepository.findAll();
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    public Optional<Income> getIncomeById(Long id) {
        return incomeRepository.findById(id);
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
