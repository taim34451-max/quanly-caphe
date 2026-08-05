package com.thanhtai.model;

public class user {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String major;

    public user() {}

    public user(String name, String email, String gender, String major) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.major = major;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
}