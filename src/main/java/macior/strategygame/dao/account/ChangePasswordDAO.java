package macior.strategygame.dao.account;

import macior.strategygame.dao.Context;
import macior.strategygame.models.account_management.PasswordCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository("changePasswordDAO")
public class ChangePasswordDAO {

    @Autowired
    private Context context;

    public int addOrUpdateCode(PasswordCode code){
        PasswordCode found = code.clone();
        found = context.entityManager().find(PasswordCode.class, found);
        if (found == null){
            return addCode(code);
        } else {
            return updateCode(code);
        }
    }

    public int updateCode(PasswordCode code){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.merge(code);
            manager.flush();
            transaction.commit();
            manager.clear();
            return 0;
        } catch (Exception exc){
            transaction.rollback();
            manager.clear();
            return -1;
        }
    }


    public int addCode(PasswordCode code){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.persist(code);
            manager.flush();
            transaction.commit();
            manager.clear();
            return 0;
        } catch (Exception exc){
            transaction.rollback();
            manager.clear();
            return -1;
        }
    }

    public Optional<PasswordCode> getCode(String code){
        EntityManager manager = context.entityManager();
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<PasswordCode> criteriaQuery = builder.createQuery(PasswordCode.class);
        Root<PasswordCode> root = criteriaQuery.from(PasswordCode.class);
        Predicate predicate = builder.equal(root.get("code"), code);
        criteriaQuery.where(predicate);
        Query query = manager.createQuery(criteriaQuery);
        Object object;
        try {
            object = query.getSingleResult();
        } catch (NoResultException exc){
            return Optional.empty();
        }
        PasswordCode passwordCode = (PasswordCode) object;
        return Optional.of(passwordCode);
    }

    public int deletePassword(PasswordCode code){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.remove(code);
            manager.flush();
            transaction.commit();
            manager.clear();
            return 0;
        } catch (Exception exc){
            transaction.rollback();
            manager.clear();
            return -1;
        }
    }
}
