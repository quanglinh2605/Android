package com.entities;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private double Price;
    private int photo;

    public Product() {
    }

    public Product(int id, String name, double price, int photo, String description) {
        this.id = id;
        this.name = name;
        Price = price;
        this.photo = photo;
        Description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description;
}
