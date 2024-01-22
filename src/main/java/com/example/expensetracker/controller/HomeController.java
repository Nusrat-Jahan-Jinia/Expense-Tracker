package com.example.expensetracker.controller;

import com.example.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {
    @Autowired
    private ExpenseService expenseService;
    @GetMapping("")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
//        long count = expenseService.getLastMonthExpenseCount();
        modelAndView.addObject("count", 50000);
        modelAndView.addObject("totalIncome", 500000);
        modelAndView.setViewName("dashboard");
        return modelAndView;
    }
}
