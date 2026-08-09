package DAO;

import java.util.List;
import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DrinkDAO {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");

    private EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public List<Product> findAll() {
        EntityManager em = getEntityManager();
        try {
            String sql = "SELECT p FROM Product p";
            TypedQuery<Product> query = em.createQuery(sql, Product.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Product findById(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public void createProduct(Product product) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void updateProduct(Product product) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void deleteProduct(int id) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
