package com.coffee.service;

import java.util.List;

import com.coffee.dao.CoffeeDAO;
import com.coffee.dao.CoffeeDAOImpl;
import com.coffee.entity.coffee;

public class CoffeeServiceImpl implements CoffeeService{
	CoffeeDAO dao = new CoffeeDAOImpl();
	
	@Override
	public coffee create (coffee coffee) {
		return dao.create(coffee);
	}
	
	@Override
	public coffee findByID(String id) {
		return dao.findByID(id);
	}
	
	@Override
	public void update (coffee coffee) {
		 dao.update(coffee);
	}
	
	@Override
	public void deleteByID (String id) {
		dao.deleteByID(id);
	}
	
	@Override
	public List<coffee> findAll(){
		return dao.findAll();
	}
	
}
