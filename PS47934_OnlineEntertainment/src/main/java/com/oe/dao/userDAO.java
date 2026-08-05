package com.oe.dao;

import java.util.List;

import com.oe.entity.user;
import com.oe.ultils.jpaUltil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class userDAO {

    public void create(user entity) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Tài khoản đã tồn tại!");
        } finally {
            em.close();
        }
    }

    public void update(user entity) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi cập nhật User");
        } finally {
            em.close();
        }
    }

    public void delete(String id) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            em.getTransaction().begin();

            user entity = em.find(user.class, id);

            if (entity != null) {
                em.remove(entity);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Lỗi xóa User");
        } finally {
            em.close();
        }
    }

    public user findById(String id) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            return em.find(user.class, id);
        } finally {
            em.close();
        }
    }

    public user findByEmail(String email) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql = "SELECT u FROM user u WHERE u.email = :email";

            return em.createQuery(jpql, user.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean isEmailExists(String email) {
        return findByEmail(email) != null;
    }

    public user checkLogin(String id, String password) {
        user u = findById(id);

        if (u != null && u.getPassword().equals(password)) {
            return u;
        }

        return null;
    }

    public List<user> findAll(int pageNumber, int pageSize) {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql = "SELECT u FROM user u";

            TypedQuery<user> query = em.createQuery(jpql, user.class);

            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public long countAll() {
        EntityManager em = jpaUltil.getEntityManager();

        try {
            String jpql = "SELECT COUNT(u) FROM user u";

            return em.createQuery(jpql, Long.class)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }
 // Hàm main chạy độc lập để test và bơm dữ liệu mẫu (Seed Data)
    public static void main(String[] args) {
        userDAO dao = new userDAO();
        user adminUser = new user();
        
        // Setup thông tin tài khoản Sếp
        adminUser.setId("admin");
        adminUser.setPassword("123456");
        adminUser.setFullname("Mai Thành Tài");
        adminUser.setEmail("tai@gmail.com");
        adminUser.setAdmin(true); // Cấp thẻ VIP tại đây
        
        try {
            dao.create(adminUser);
            System.out.println("Chúc mừng sếp! Đã tạo tài khoản Admin thành công.");
        } catch (Exception e) {
            System.out.println("Tạo thất bại (Có thể tài khoản 'admin' đã tồn tại). Lỗi: " + e.getMessage());
        }
    }
}