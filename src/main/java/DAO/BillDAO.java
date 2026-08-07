package DAO;

import java.util.List;

import Entity.Bill;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class BillDAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyCoffee");
	EntityManager em = factory.createEntityManager();
	
public List<Bill> findAll() {
		
		String sql = "select o from Bill o";
		TypedQuery<Bill> query = em.createQuery(sql,Bill.class);
		List<Bill> list = query.getResultList();
		return list;
	}
	
	public Bill FindByID(String id) {
		Bill users = em.find(Bill.class, id);
		return users;
	}
	
	public void CreateBill(Bill u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}
	}
	
	public void UpdateBill(Bill u) {
		Bill exit = em.find(Bill.class, u.getBillId());
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
		Bill exit = em.find(Bill.class, id);
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
