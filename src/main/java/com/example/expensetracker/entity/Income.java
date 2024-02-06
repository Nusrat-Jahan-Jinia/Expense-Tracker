package com.example.expensetracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.math.BigDecimal;


@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.EAGER)
    private Tag tag;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    public Income() {
    }

    public Income(Long id, String source, BigDecimal amount, Tag tag, LocalDate date) {
        this.id = id;
        this.source = source;
        this.amount = amount;
        this.tag = tag;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", amount=" + amount +
                ", tag=" + tag +
                ", date=" + date +
                '}';
    }
}
