package com.entities;

import java.io.Serializable;

public class Weather implements Serializable {
    private int photo;
    private String status;
    private String city;
    private double temp;

    public Weather() {
    }

    public Weather(int photo, String status, String city, double temp) {
        this.photo = photo;
        this.status = status;
        this.city = city;
        this.temp = temp;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
