package DAO;

import java.util.List;

import Entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DrinkDAO {


	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");
	EntityManager em = factory.createEntityManager();
	
public List<Product> findAll() {
	
		
		String sql = "select o from Product o";
		TypedQuery<Product> query = em.createQuery(sql,Product.class);
		List<Product> list = query.getResultList();
		return list;
	}
	
	public Product FindByID(int id) {
		return em.find(Product.class, id);
	}
	
	public void CreateBill(Product u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
	}
	
	public void UpdateBill(Product u) {
		Product exit = em.find(Product.class, u.getProductId());
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
	
	public void DeleteBill(int id) {
		Product exit = em.find(Product.class, id);
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
