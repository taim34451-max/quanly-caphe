package com.thanhtai.model;

public class ModelEmployee {
    private String id;
    private String name;
    private boolean gender;
    private double salary;

    // Constructor mặc định (Bắt buộc phải có để Jackson hoạt động)
    public ModelEmployee() {
    }

    // Constructor đầy đủ tham số
    public ModelEmployee(String id, String name, boolean gender, double salary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }

    // Getter và Setter
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

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}