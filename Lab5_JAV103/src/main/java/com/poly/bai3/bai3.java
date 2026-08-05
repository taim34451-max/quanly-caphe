package com.poly.bai3;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

// Gọi entity user viết thường theo chuẩn của bạn
import com.poly.entity.user; 

public class bai3 {
    public static void main(String[] args) {
        
        // 1. Mở cầu nối đến CSDL thông qua tên cấu hình trong file persistence.xml
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
        EntityManager em = factory.createEntityManager();

        try {
            // 2. Định nghĩa câu lệnh JPQL (Lưu ý: Truy vấn trên Entity 'user', không phải bảng SQL)
            // Ký hiệu :search và :role là các "biến chờ" (Named Parameters)
            String jpql = "SELECT o FROM user o WHERE o.email LIKE :search AND o.admin = :role";

            // 3. Tạo TypedQuery để JPA hiểu rằng kết quả trả về sẽ được ép kiểu chuẩn thành các object 'user'
            TypedQuery<user> query = em.createQuery(jpql, user.class);

            // 4. Bơm giá trị thật vào các "biến chờ". 
            // Dấu % đại diện cho bất kỳ chuỗi ký tự nào đứng trước đuôi @fpt.edu.vn
            query.setParameter("search", "%@fpt.edu.vn");
            query.setParameter("role", false); // false tức là Role = User (Không phải Admin)

            // 5. Thực thi truy vấn và hứng kết quả vào một List
            List<user> list = query.getResultList();

            // 6. Xử lý logic hiển thị ra Console
            System.out.println("=== DANH SÁCH TÀI KHOẢN SINH VIÊN FPT (ROLE: USER) ===");
            if (list.isEmpty()) {
                System.out.println("Không tìm thấy user nào thỏa mãn điều kiện!");
            } else {
                for (user u : list) {
                    // Trích xuất đúng 2 thông tin theo yêu cầu đề bài: Họ tên và Email
                    System.out.println("- Họ tên: " + u.getFullname() + " | Email: " + u.getEmail());
                }
            }
            System.out.println("======================================================");

        } catch (Exception e) {
            System.out.println("Lỗi truy vấn CSDL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 7. Giải phóng bộ nhớ, đóng kết nối (Trì hoãn sự sung sướng, làm xong phải dọn dẹp)
            em.close();
            factory.close();
        }
    }
}