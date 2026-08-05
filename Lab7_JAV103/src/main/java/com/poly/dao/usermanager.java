package com.poly.dao;

import java.util.List;

import com.poly.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class usermanager {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = factory.createEntityManager();

    // AI Check Email
    public boolean checkEmailAI(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(regex);
    }

    public List<User> findAll() {
        String jpql = "SELECT o FROM user o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList(); 
    }

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public void create(User newUser) {
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

    public void update(User updatedUser) {
        try {
            em.getTransaction().begin();
            em.merge(updatedUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void deleteById(String id) {
        User u = em.find(User.class, id);
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
    
    public List<User> findFptUsers() {
        String jpql = "SELECT o FROM user o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        return query.getResultList(); 
    }
}