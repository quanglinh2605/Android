package com.entities;

import java.io.Serializable;

public class Logo implements Serializable {
    private String name;
    private int photo;
    private String money;

    public Logo() {
    }

    public Logo(String name, int photo, String money) {
        this.name = name;
        this.photo = photo;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
