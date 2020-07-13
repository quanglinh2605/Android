package com.entities;

import java.io.Serializable;

public class Work implements Serializable {
    private int id;
    private String name;
    private String creationDate;
    private String description;
    private String priority;

    public Work() {
    }

    public Work(int id, String name, String creationDate, String description, String priority) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.description = description;
        this.priority = priority;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
