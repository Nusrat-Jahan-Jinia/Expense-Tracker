package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.IncomeService;
import com.example.expensetracker.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private IncomeService incomeService;

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();

        // today and last month date calculation
        LocalDate today = LocalDate.now();
        String formattedToday = Utils.getFormattedDate(today);
        LocalDate lastMonthDate = today.minusMonths(1);
        String formattedLastMonth = Utils.getFormattedDate(lastMonthDate);

        // count last month total expense
        double totalExpense = expenseService.getTotalAmountAfterLastMonth();
        double totalIncome = incomeService.getTotalAmountAfterLastMonth();
        double totalSavings = totalIncome - totalExpense;

        modelAndView.addObject("totalExpense", totalExpense);
        modelAndView.addObject("totalIncome", totalIncome);
        modelAndView.addObject("totalSavings", totalSavings);
        modelAndView.addObject("count", 4000);
        modelAndView.addObject("today", formattedToday);
        modelAndView.addObject("lastMonth", formattedLastMonth);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
