package macior.strategygame.dao.account;

import macior.strategygame.dao.Context;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.LoginCode;
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
import javax.transaction.TransactionManager;
import java.util.List;
import java.util.Optional;

@Repository("changeLoginDAO")
public class ChangeLoginDAO {

    @Autowired
    private Context context;

    public int existsLogin(String login){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<LoginCode> criteria = criteriaBuilder.createQuery(LoginCode.class);
        Root<LoginCode> codeRoot = criteria.from(LoginCode.class);
        Predicate loginPredicate = criteriaBuilder.equal(codeRoot.get("login"), login);
        criteria.where(loginPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<LoginCode> result = criteriaQuery.getResultList();
        if (result.size() > 0){
            return 1;
        } else {
            return 0;
        }
    }

    public int changeCode(LoginCode code){
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
        }
        manager.clear();
        return 1;
    }

    public int addOrUpdateCode(LoginCode code){
        EntityManager manager = context.entityManager();
        LoginCode existingCode = new LoginCode();
        existingCode.setUser(code.getUser());
        existingCode = manager.find(LoginCode.class, existingCode);
        if (existingCode != null){
            return changeCode(code);
        } else {
            return addCode(code);
        }
    }

    public int addCode(LoginCode code){
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
        }
        manager.clear();
        return 1;
    }

    public int deleteLogin(LoginCode code){
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

    public Optional<LoginCode> getCode(String code){
        CriteriaBuilder builder = context.criteriaBuilder();
        CriteriaQuery<LoginCode> criteriaQuery = builder.createQuery(LoginCode.class);
        Root<LoginCode> root = criteriaQuery.from(LoginCode.class);
        Predicate predicate = builder.equal(root.get("code"), code);
        criteriaQuery.where(predicate);
        Query query = context.entityManager().createQuery(criteriaQuery);
        Object object;
        try {
            object = query.getSingleResult();
        } catch (NoResultException exc){
            return Optional.empty();
        }
        LoginCode loginCode = (LoginCode)object;
        return Optional.of(loginCode);
    }
}
