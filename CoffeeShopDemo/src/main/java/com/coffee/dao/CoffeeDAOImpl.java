package com.coffee.dao;

import java.util.List;

import com.coffee.entity.coffee;
import com.coffee.utils.XJpa;

import jakarta.persistence.EntityManager;

public class CoffeeDAOImpl implements CoffeeDAO{
	
	
	@Override
	public coffee create (coffee entity) {
		EntityManager em = XJpa.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
		
		return entity;
	}
	
	
	@Override
	public void update(coffee entity) {
		EntityManager em = XJpa.getEntityManager();
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	
	@Override
	public void deleteByID(String id) {
		EntityManager em = XJpa.getEntityManager();
		em.getTransaction().begin();
		try {
			var entity = em.find(coffee.class, id);
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

	}
	
	@Override
	public coffee findByID(String id) {
		EntityManager em = XJpa.getEntityManager();
		return em.find(coffee.class, id);
	}
	
	@Override
	public 	List<coffee> findAll() {
		EntityManager em = XJpa.getEntityManager();
		var jpql = "SELECT o FROM coffee o";
		var query = em.createQuery(jpql, coffee.class);
		return query.getResultList();
	}

	
}
