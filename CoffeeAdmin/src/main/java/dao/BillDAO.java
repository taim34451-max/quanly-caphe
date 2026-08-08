package dao;

import entity.Bill;
import utils.XJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BillDAO {
    
    public Bill findById(String id) {
        EntityManager em = XJpa.getEntityManager();
        try {
            return em.find(Bill.class, id);
        } finally {
            em.close();
        }
    }

    public List<Bill> findAll() {
        EntityManager em = XJpa.getEntityManager();
        try {
            TypedQuery<Bill> query = em.createQuery("SELECT b FROM Bill b", Bill.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Bill bill) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(bill);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Bill bill) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(bill);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(String id) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            Bill bill = em.find(Bill.class, id);
            if (bill != null) {
                em.remove(bill);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    
    public List<Object[]> getSalesReport() {
        EntityManager em = XJpa.getEntityManager();
        try {
            String jpql = "SELECT b.drink.drinkName, SUM(b.quantity), SUM(b.quantity * b.drink.drinkPrice) FROM BillDetail b GROUP BY b.drink.drinkName";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
