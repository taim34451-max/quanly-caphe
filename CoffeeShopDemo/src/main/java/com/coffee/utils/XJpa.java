package com.coffee.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJpa {
	static EntityManagerFactory factory;

    public static EntityManager getEntityManager() {

        if(factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("CoffeeShopDemo");
        }

        return factory.createEntityManager();
    }
}
