package macior.strategygame.dao.friendship;

import macior.strategygame.dao.Context;
import macior.strategygame.models.User;
import macior.strategygame.models.friends.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
}
