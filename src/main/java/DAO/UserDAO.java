package DAO;

import java.util.List;

import Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class UserDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");
	EntityManager em = factory.createEntityManager();
	
	public List<Users> findAll() {
		
		String sql = "select o from Users o";
		TypedQuery<Users> query = em.createQuery(sql,Users.class);
		List<Users> list = query.getResultList();
		return list;
	}
	
	public Users FindByID(String id) {
		Users users = em.find(Users.class, id);
		System.out.println(users);
		return users;
	}
	
	public void CreateUser(Users u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
	}
	
	public void UpdateUser(Users u) {
		Users exit = em.find(Users.class, u.getIDUser());
		if (exit!=null) {
			try {
				em.getTransaction().begin();
				em.merge(u);
				em.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				em.getTransaction().rollback();
			}
		}
	}
	
	public void DeleteUsers(String id) {
		Users exit = em.find(Users.class, id);
		String sql = "delete from Bill o where o.users.iduser like :id";
		String sql2 = "delete from BillDetail o where o.Bill.users.iduser like :id";
		try {
			em.getTransaction().begin();
			em.createQuery(sql).setParameter("id", id).executeUpdate();
			em.createQuery(sql2).setParameter("id", id).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
		
		if(exit!=null) {
			try {
				em.getTransaction().begin();
				em.remove(exit);
				em.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				em.getTransaction().rollback();
			}
		}
	}
}
