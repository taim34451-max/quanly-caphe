package DAO;

import java.util.List;

import Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserDAO {
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");

    private EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
	
    public List<Users> findAll() { 
        EntityManager em = getEntityManager();
        try {
            String sql = "SELECT u FROM Users u";
            TypedQuery<Users> query = em.createQuery(sql, Users.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
 // Tìm kiếm người dùng theo Email
    public Users findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.email = :email";
            TypedQuery<Users> query = em.createQuery(jpql, Users.class);
            query.setParameter("email", email);
            List<Users> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Search by Primary Key (UserId - Integer)
    public Users findById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    // Search by Username (String) for Login - Matches your servlet call
    public Users FindByID(String username) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.userName = :username";
            TypedQuery<Users> query = em.createQuery(jpql, Users.class);
            query.setParameter("username", username);
            List<Users> list = query.getResultList();
            return list.isEmpty() ? null : list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public void CreateUser(Users u) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(u);
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

    public void UpdateUser(Users u) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(u);
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

    public void DeleteUser(Integer userId) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Users exit = em.find(Users.class, userId);
            if (exit != null) {
                em.remove(exit);
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
