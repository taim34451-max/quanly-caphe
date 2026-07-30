package com.coffee.service;

import java.util.List;

import com.coffee.entity.coffee;

public interface CoffeeService {
	coffee create (coffee coffee);
	coffee findByID(String id);
	void update (coffee coffee);
	void deleteByID (String id);
	List<coffee> findAll();
}
