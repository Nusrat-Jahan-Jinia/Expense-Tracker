package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ProductRepository;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.HomeService;
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
    private final ExpenseService expenseService;
    private final IncomeService incomeService;
    private final HomeService homeService;

    @Autowired
    public HomeController(ExpenseService expenseService, IncomeService incomeService, HomeService homeService) {
        this.expenseService = expenseService;
        this.incomeService = incomeService;
        this.homeService = homeService;
    }

    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        String today = Utils.getToday();
        String lastMonth = Utils.getLastMonth();
        double totalExpense = homeService.getTotalExpenseAfterLastMonth();
        double totalIncome = homeService.getTotalIncomeAfterLastMonth();
        double totalSavings = homeService.getTotalSavingsAfterLastMonth();

        modelAndView.addObject("totalExpense", totalExpense);
        modelAndView.addObject("totalIncome", totalIncome);
        modelAndView.addObject("totalSavings", totalSavings);
        modelAndView.addObject("count", 4000);
        modelAndView.addObject("today", today);
        modelAndView.addObject("lastMonth", lastMonth);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
