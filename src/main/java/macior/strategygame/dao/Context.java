package macior.strategygame.dao;

import org.hibernate.Criteria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.beans.BeanProperty;

@Configuration
public class Context {

    private final String persistenceUnitName;
    private final EntityManagerFactory factory;
    private final EntityManager entityManager;
    private final EntityTransaction transaction;
    private final CriteriaBuilder criteriaBuilder;


    public Context(){
        persistenceUnitName = "strategyGamePersistence";
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    @Bean
    public EntityManager entityManager(){
        return entityManager;
    }

    @Bean public EntityTransaction transaction(){
        return transaction;
    }

    @Bean public CriteriaBuilder criteriaBuilder(){
        return criteriaBuilder;
    }

}
