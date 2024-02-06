package com.example.expensetracker.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue
    private Long id;
    private String orderBy;
    private String orderDate;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private Set<Product> products = new HashSet<>();

    @Transient
    private int orderId;

    public ProductOrder() {
    }

    public ProductOrder(Long id, String orderBy, String orderDate, Customer customer, Set<Product> products, int orderId) {
        this.id = id;
        this.orderBy = orderBy;
        this.orderDate = orderDate;
        this.customer = customer;
        this.products = products;
        this.orderId = orderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
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
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
