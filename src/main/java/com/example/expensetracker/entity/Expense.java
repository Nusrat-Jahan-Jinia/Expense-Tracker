package com.example.expensetracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer amount;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

//    @Column(name="created_at", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    @JsonIgnore
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @CreationTimestamp
//    @JsonIgnore
//    private LocalDateTime updatedAt;

    public Expense() {
    }

    public Expense(
            Integer id,
            String title,
            Integer amount,
            LocalDate date,
            Category category
//            LocalDateTime createdAt,
//            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.amount = amount;
        this.category = category;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }

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
