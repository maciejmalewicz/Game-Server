package macior.strategygame.dao.activationLinks;

import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.User;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

//todo test it!
@Repository
public class ActivationDAO {

    @Autowired
    private IActivationLinkDAO activationLinkDAO;

    @Autowired
    private IUserDAO userDAO;

    @Transactional
    public int activateLink(String link){
        Optional<ActivationLink> optional = activationLinkDAO.findFirstByActivationLink(link);
        if (optional.isEmpty()){
            return MenuErrors.CODE_NOT_FOUND;
        }
        ActivationLink activationLink = optional.get();
        User userToAdd = new User(activationLink);
        try {
            activationLinkDAO.delete(activationLink);
            userDAO.save(userToAdd);
            return 0;
        } catch (Exception exc){
            return MenuErrors.INTERNAL_ERROR;
        }
    }


}
