package macior.strategygame.service;

import macior.strategygame.dao.activationLinks.ActivationLinkDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.ActivationLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActivationLinkService {

    private final ActivationLinkDAO activationLinkDAO;
    private final UserDAO userDAO;

    @Autowired
    public ActivationLinkService(@Qualifier("activationLinkDAO") ActivationLinkDAO activationLinkDAO,
    @Qualifier("userDAO") UserDAO userDAO){
        this.activationLinkDAO = activationLinkDAO;
        this.userDAO = userDAO;
    }

    public int addActivationLink(ActivationLink link){ //0 if all is fine, 1 if login is occupied and 2 if email is used already
        System.out.println("serviced: " + link);   //and 3 if password is wrong
        String login = link.getLogin();
        String email = link.getEmail();
        int passwordVal;

        int userResult = userDAO.existsInUsers(login, email);
        int linkResult = activationLinkDAO.existsInLinks(login, email);

        if (userResult == 2 || linkResult == 2){
            return 2;
        } else if (userResult == 1 || linkResult == 1){
            return 1;
        } else if ((passwordVal = PasswordValidator.isValid(link.getPassword())) != 0){
            return passwordVal;
        } else {
            activationLinkDAO.add(UUID.randomUUID(), link);
            EMAILSender.sendActivationLink(link);
            return 0;
        }
    }

}
