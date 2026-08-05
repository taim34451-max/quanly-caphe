package com.poly.dao;

import com.poly.entity.user;

public class usertest {

    public static void main(String[] args) {
        usermanager manager = new usermanager();

        System.out.println("=== KIỂM TRA THÊM MỚI & AI ===");
        // Test AI: Cố tình nhập email sai
        manager.create(new user("U01", "123", "Lỗi Định Dạng", "toilao", false));
        
        // Nhập dữ liệu chuẩn
        manager.create(new user("U02", "123", "Nguyễn Văn Tèo", "teonv@gmail.com", false));
        manager.create(new user("U03", "123", "Sếp Lớn", "admin@congty.com", true));
        manager.create(new user("U04", "123", "Mai Thành Tài", "taimt@fpt.edu.vn", false)); // Đạt chuẩn bài 3

        System.out.println("\n=== KIỂM TRA TÌM KIẾM ===");
        manager.findById("U02");

        System.out.println("\n=== KIỂM TRA BÀI 3 (TÌM SINH VIÊN FPT) ===");
        manager.findFptUsers();

        // Đóng chương trình
        System.exit(0);
    }
}