package com.example.expensetracker.util;

import jakarta.persistence.PrePersist;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Utils {

public static String getFormattedDate(LocalDate date){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    String formattedDate = date.format(formatter);
    return formattedDate;
}
}
