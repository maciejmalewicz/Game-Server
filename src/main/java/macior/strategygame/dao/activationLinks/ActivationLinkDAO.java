package macior.strategygame.dao.activationLinks;

import macior.strategygame.dao.AbstractDAO;
import macior.strategygame.dao.Context;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("activationLinkDAO")
public class ActivationLinkDAO extends AbstractDAO<ActivationLink> implements IActivationLinkDAO{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    public ActivationLinkDAO(Context context){
        super(context);
    }

    public int existsInLinks(String login, String mail){ //0-does not, 1-login, 2-mail
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<ActivationLink> criteria = criteriaBuilder.createQuery(ActivationLink.class);
        Root<ActivationLink> userRoot = criteria.from(ActivationLink.class);
        Predicate loginPredicate = criteriaBuilder.equal(userRoot.get("login"), login);
        criteria.where(loginPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<User> result = criteriaQuery.getResultList();
        if (result.size() > 0){
            return 1;
        }

        CriteriaQuery<ActivationLink> criteria2 = criteriaBuilder.createQuery(ActivationLink.class);
        Root<ActivationLink> userRoot2 = criteria2.from(ActivationLink.class);
        Predicate emailPredicate = criteriaBuilder.equal(userRoot2.get("email"), mail);
        criteria2.where(emailPredicate);
        Query criteriaQuery2 = context.entityManager().createQuery(criteria2);
        result = criteriaQuery2.getResultList();
        if (result.size() > 0){
            return 2;
        }

        return 0;
    }

    public int existsLogin(String login){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<ActivationLink> criteria = criteriaBuilder.createQuery(ActivationLink.class);
        Root<ActivationLink> userRoot = criteria.from(ActivationLink.class);
        Predicate loginPredicate = criteriaBuilder.equal(userRoot.get("login"), login);
        criteria.where(loginPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);
        List<User> result = criteriaQuery.getResultList();
        if (result.size() > 0){
            return 1;
        } else {
            return 0;
        }
    }

    public void add(ActivationLink link){
        System.out.println("adding: " + link + " with uuid: " + link.getActivationLink());
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.persist(link);
            manager.flush();
            transaction.commit();
        } catch (Exception exc){
            exc.printStackTrace();
            transaction.rollback();
        }
        manager.clear();
    }

    public int activate(String link){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<ActivationLink> criteria = criteriaBuilder.createQuery(ActivationLink.class);
        Root<ActivationLink> linkRoot = criteria.from(ActivationLink.class);
        Predicate loginPredicate = criteriaBuilder.equal(linkRoot.get("activationLink"), link);
        criteria.where(loginPredicate);
        Query criteriaQuery = context.entityManager().createQuery(criteria);

        List<ActivationLink> result = criteriaQuery.getResultList();

        if (result.size() > 0){
            ActivationLink linkToActivate = result.get(0);
            delete(linkToActivate.getId());
            User userToAdd = new User(linkToActivate);
            userDAO.add(userToAdd);
            return 0;
        }
        else {
            return 2;
        }
    }

    public Optional<ActivationLink> getById(int id){
        EntityManager manager = context.entityManager();
        Optional<ActivationLink> out = Optional.of(manager.find(ActivationLink.class, id));
        return out;
    }
}
