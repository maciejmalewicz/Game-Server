package macior.strategygame.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Component
public class Context {

    private final EntityManagerFactory factory;

    public Context(){
        String persistenceUnitName = "strategyGamePersistence";
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
    }

    public EntityManager entityManager(){
        return factory.createEntityManager();
    }



}
