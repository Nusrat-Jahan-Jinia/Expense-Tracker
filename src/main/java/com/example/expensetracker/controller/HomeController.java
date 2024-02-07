package com.example.expensetracker.controller;

import com.example.expensetracker.service.HomeService;
import com.example.expensetracker.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;


@Controller
@RequestMapping("")
public class HomeController {
    private final HomeService homeService;

    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping(value = "")
    public String getHome(Model model) {
        String today = Utils.getToday();
        String lastMonth = Utils.getLastMonth();
        BigDecimal totalExpense = homeService.getTotalExpenseAfterLastMonth();
        BigDecimal totalIncome = homeService.getTotalIncomeAfterLastMonth();
        BigDecimal totalSavings = homeService.getTotalSavingsAfterLastMonth();
        model.addAttribute("totalExpense", totalExpense);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalSavings", totalSavings);
        model.addAttribute("count", 4000);
        model.addAttribute("today", today);
        model.addAttribute("lastMonth", lastMonth);
        model.addAttribute("lastMonth", lastMonth);
        return "dashboard";
    }
}
