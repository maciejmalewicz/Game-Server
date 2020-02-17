package macior.strategygame.dao.activationLinks;

import macior.strategygame.dao.AbstractDAO;
import macior.strategygame.dao.Context;
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

    public void add(UUID id, ActivationLink link){
        System.out.println("adding: " + link + " with uuid: " + id);
        link.setActivationLink(id.toString());
        System.out.println(link.getActivationLink());
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

    public Optional<ActivationLink> getById(int id){
        EntityManager manager = context.entityManager();
        Optional<ActivationLink> out = Optional.of(manager.find(ActivationLink.class, id));
        return out;
    }
}
