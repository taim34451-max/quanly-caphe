package utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJpa {
    private static final EntityManagerFactory FACTORY;

    static {
        try {
            FACTORY = Persistence.createEntityManagerFactory("PolyCoffee");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
