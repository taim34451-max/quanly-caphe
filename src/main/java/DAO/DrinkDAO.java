package DAO;

import java.util.List;

import Entity.Drink;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DrinkDAO {


	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");
	EntityManager em = factory.createEntityManager();
	
public List<Drink> findAll() {
	
		
		String sql = "select o from Drink o";
		TypedQuery<Drink> query = em.createQuery(sql,Drink.class);
		List<Drink> list = query.getResultList();
		return list;
	}
	
	public Drink FindByID(String id) {
		Drink users = em.find(Drink.class, id);
		return users;
	}
	
	public void CreateBill(Drink u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
	}
	
	public void UpdateBill(Drink u) {
		Drink exit = em.find(Drink.class, u.getIDDrink());
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
	
	public void DeleteBill(String id) {
		Drink exit = em.find(Drink.class, id);
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
