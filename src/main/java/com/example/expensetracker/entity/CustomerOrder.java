package com.example.expensetracker.entity;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue
    private int id;
    private String orderBy;
    private String orderDate;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> product;

    public CustomerOrder() {
    }

    public CustomerOrder(int id, String orderBy, String orderDate, Customer customer, List<Product> product) {
        this.id = id;
        this.orderBy = orderBy;
        this.orderDate = orderDate;
        this.customer = customer;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "id=" + id +
                ", orderBy='" + orderBy + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                '}';
    }
}
