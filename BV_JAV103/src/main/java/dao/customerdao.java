package dao;

import entity.customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class customerdao {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JAV103_DB");

    public void create(customer entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void update(customer entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            customer entity = em.find(customer.class, username);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public customer findById(String username) {
        EntityManager em = emf.createEntityManager();
        customer entity = em.find(customer.class, username);
        em.close();
        return entity;
    }

    public List<customer> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<customer> query = em.createQuery("SELECT c FROM customer c", customer.class);
        List<customer> list = query.getResultList();
        em.close();
        return list;
    }
}