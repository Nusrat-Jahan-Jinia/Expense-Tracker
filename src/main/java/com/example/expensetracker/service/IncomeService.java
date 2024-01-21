package com.example.expensetracker.service;

import com.example.expensetracker.entity.Income;
import com.example.expensetracker.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    public List<Income> getIncomes(){
        return (List<Income>) incomeRepository.findAll();
    }
}
