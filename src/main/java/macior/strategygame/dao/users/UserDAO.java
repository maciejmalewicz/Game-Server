package macior.strategygame.dao.users;

import macior.strategygame.dao.AbstractDAO;
import macior.strategygame.dao.Context;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


@Repository("userDAO")
public class UserDAO extends AbstractDAO<User> {

    @Autowired
    public UserDAO(Context context){
        super(context);
    }

    public Optional<User> getById(int id){
        EntityManager manager = context.entityManager();
        Optional<User> out = Optional.of(manager.find(User.class, id));
        return out;
    }

    public int existsInUsers(String login, String email){ //same as for link
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate loginPredicate = criteriaBuilder.equal(userRoot.get("login"), login);
        criteria.where(loginPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<User> result = criteriaQuery.getResultList();
        if (result.size() > 0){
            return 1;
        }

        CriteriaQuery<User> criteria2 = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot2 = criteria2.from(User.class);
        Predicate emailPredicate = criteriaBuilder.equal(userRoot2.get("email"), email);
        criteria2.where(emailPredicate);
        Query criteriaQuery2 = context.entityManager().createQuery(criteria2);
        result = criteriaQuery2.getResultList();
        if (result.size() > 0) {
            return 2;
        }
        return 0;
    }


    public Optional<User> getAccount(String login, String password){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<User> criteria = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteria.from(User.class);
        Predicate loginPredicate = criteriaBuilder.equal(userRoot.get("login"), login);
        Predicate passwordPredicate = criteriaBuilder.equal(userRoot.get("password"), password);

        criteria.where(criteriaBuilder.and(loginPredicate, passwordPredicate));

        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<User> result = criteriaQuery.getResultList();
        if (result.size() > 0){
            return Optional.of(result.get(0));
        }
        else return Optional.empty();
    }
}
