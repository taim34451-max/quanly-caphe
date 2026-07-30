package com.coffee.dao;

import java.util.List;

import com.coffee.entity.coffee;

public interface CoffeeDAO {

	coffee create (coffee coffee);
	coffee findByID(String id);
	void update (coffee coffee);
	void deleteByID (String id);
	List<coffee> findAll();
}
