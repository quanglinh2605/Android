package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Invoice implements Serializable {
    private String id;
    private String name;
    private Date created;
    private String payment;
    private List<Product> products;
    private Customer customer;

    public Invoice() {
    }

    public Invoice(String id, String name, Date created, String payment, List<Product> products, Customer customer) {
        this.id = id;
        this.name = name;
        this.created = created;
        this.payment = payment;
        this.products = products;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
