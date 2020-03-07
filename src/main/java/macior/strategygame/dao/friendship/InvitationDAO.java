package macior.strategygame.dao.friendship;

import macior.strategygame.dao.Context;
import macior.strategygame.models.User;
import macior.strategygame.models.friends.Invitation;

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
import java.util.List;


@Repository("invitationDAO")
public class InvitationDAO {

    @Autowired
    private Context context;

    public int sendInvitation(Invitation invitation){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.persist(invitation);
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

    public List<Invitation> getInvitations(User user){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<Invitation> criteriaQuery = criteriaBuilder.createQuery(Invitation.class);
        Root<Invitation> root  = criteriaQuery.from(Invitation.class);
        Predicate predicate = criteriaBuilder.equal(root.get("receiver"), user.getId());
        criteriaQuery.where(predicate);
        Query query = context.entityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Invitation> getSentInvitations(User user){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<Invitation> criteriaQuery = criteriaBuilder.createQuery(Invitation.class);
        Root<Invitation> root = criteriaQuery.from(Invitation.class);
        Predicate predicate = criteriaBuilder.equal(root.get("sender"), user.getId());
        criteriaQuery.where(predicate);
        Query query = context.entityManager().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public Invitation findInvitation(User sender, User receiver){
        CriteriaBuilder criteriaBuilder = context.criteriaBuilder();
        CriteriaQuery<Invitation> criteriaQuery = criteriaBuilder.createQuery(Invitation.class);
        Root<Invitation> root = criteriaQuery.from(Invitation.class);
        Predicate senderPredicate = criteriaBuilder.equal(root.get("sender"), sender.getId());
        Predicate receiverPredicate = criteriaBuilder.equal(root.get("receiver"), receiver.getId());
        Predicate completePredicate = criteriaBuilder.and(senderPredicate, receiverPredicate);
        criteriaQuery.where(completePredicate);
        Query query = context.entityManager().createQuery(criteriaQuery);
        try {
            Invitation invitation = (Invitation) query.getSingleResult();
            return invitation;
        } catch (Exception exc){
            return null;
        }
    }

    public int deleteInvitation(Invitation invitation){
        EntityManager manager = context.entityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        try {
            manager.remove(invitation);
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
