package dao;

import entity.Drink;
import utils.XJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class DrinkDAO {
    
    public Drink findById(String id) {
        EntityManager em = XJpa.getEntityManager();
        try {
            return em.find(Drink.class, id);
        } finally {
            em.close();
        }
    }

    public List<Drink> findAll() {
        EntityManager em = XJpa.getEntityManager();
        try {
            TypedQuery<Drink> query = em.createQuery("SELECT d FROM Drink d", Drink.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Drink drink) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(drink);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void update(Drink drink) {
        EntityManager em = XJpa.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(drink);
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
            Drink drink = em.find(Drink.class, id);
            if (drink != null) {
                em.remove(drink);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}