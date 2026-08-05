package com.thanhtai.Bai2.model;

public class User {
    private String username;
    private String fullname;
    private boolean admin;

    public User(String username, String fullname, boolean admin) {
        this.username = username;
        this.fullname = fullname;
        this.admin = admin;
    }

    public String getFullname() { return fullname; }
    public boolean isAdmin() { return admin; }
}