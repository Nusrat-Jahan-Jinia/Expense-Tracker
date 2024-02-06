package com.example.expensetracker.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private BigDecimal amount;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    public Expense() {
    }

    public Expense(Long id, String title, BigDecimal amount, LocalDate date, Category category) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category +
                '}';
    }
}
