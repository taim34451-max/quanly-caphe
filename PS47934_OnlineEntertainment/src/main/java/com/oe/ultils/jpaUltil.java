package com.oe.ultils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class jpaUltil {
    
    // Khai báo Factory ở dạng static để dùng chung cho toàn bộ ứng dụng
    private static EntityManagerFactory factory;

    // Hàm cấp phát EntityManager cho các file DAO
    public static EntityManager getEntityManager() {
        // Chỉ khởi tạo Factory nếu nó chưa tồn tại hoặc đã bị đóng
        if (factory == null || !factory.isOpen()) {
            // Tên "OnlineEntertainment" BẮT BUỘC phải khớp 100% với thẻ <persistence-unit name="..."> trong file persistence.xml
            factory = Persistence.createEntityManagerFactory("OnlineEntertainment");
        }
        return factory.createEntityManager();
    }
    
    // Hàm đóng kết nối an toàn (Thường được gọi khi tắt Server)
    public static void shutDown() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }
}