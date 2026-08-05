package model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class main {
    public static void main(String[] args) {
        System.out.println("Bắt đầu kết nối CSDL...");

        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
            EntityManager em = factory.createEntityManager();

            System.out.println("Kết nối thành công! Các Entity đã được ánh xạ. Hãy kiểm tra SQL Server.");

            em.close();
            factory.close();
        } catch (Exception e) {
            System.out.println("Kết nối thất bại, in ra lỗi:");
            e.printStackTrace();
        }
    }
}