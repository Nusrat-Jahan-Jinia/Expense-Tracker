package com.example.expensetracker.util;

import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.UUID;

public class Utils {

public static String getFormattedDate(LocalDate date){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    String formattedDate = date.format(formatter);
    return formattedDate;
}

    public static String getToday() {
        LocalDate today = LocalDate.now();
        String formattedToday = Utils.getFormattedDate(today);
        return formattedToday;
    }

    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        LocalDate lastMonthDate = today.minusMonths(1);
        String formattedLastMonth = Utils.getFormattedDate(lastMonthDate);
        return formattedLastMonth;
    }
}
