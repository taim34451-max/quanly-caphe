package com.ThanhTai.Model;

import java.util.Date;

public class item {
    private String name;
    private String image;
    private double price;
    private double discount;
    private Date date = new Date();

    public item(String name, String image, double price, double discount) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }

    // Tự sinh Getters và Setters cho các thuộc tính nhé (Alt + Insert trong IDE)
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscount() { return discount; }
    public Date getDate() { return date; }
    // ... thêm các getter/setter còn lại
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}