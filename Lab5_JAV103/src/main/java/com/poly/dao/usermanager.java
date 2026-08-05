package com.poly.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import com.poly.entity.user;

public class usermanager {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = factory.createEntityManager();

    // AI Check Email
    public boolean checkEmailAI(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(regex);
    }

    // 1. TRẢ VỀ DANH SÁCH thay vì in ra console
    public List<user> findAll() {
        String jpql = "SELECT o FROM user o";
        TypedQuery<user> query = em.createQuery(jpql, user.class);
        return query.getResultList(); 
    }

    // 2. TRẢ VỀ 1 USER ĐỂ ĐƯA LÊN FORM SỬA
    public user findById(String id) {
        return em.find(user.class, id);
    }

    public void create(user newUser) {
        if (!checkEmailAI(newUser.getEmail())) {
            System.out.println("Email sai định dạng, chặn không lưu!");
            return;
        }
        try {
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update(user updatedUser) {
        try {
            em.getTransaction().begin();
            em.merge(updatedUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteById(String id) {
        user u = em.find(user.class, id);
        if (u != null) {
            try {
                em.getTransaction().begin();
                em.remove(u);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        }
    }
    
    public List<user> findFptUsers() {
        String jpql = "SELECT o FROM user o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<user> query = em.createQuery(jpql, user.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        return query.getResultList(); 
    }
}