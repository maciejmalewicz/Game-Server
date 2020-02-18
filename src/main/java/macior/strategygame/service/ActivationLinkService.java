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
        System.out.println("serviced: " + link);   //and 3+ if password is wrong AND -1 if e-mail address is wrong
        String login = link.getLogin();
        String email = link.getEmail();
        int passwordVal;

        int userResult = userDAO.existsInUsers(login, email);
        int linkResult = activationLinkDAO.existsInLinks(login, email);

        if (userResult == 2 || linkResult == 2){ //if email is used
            return 2;
        } else if (userResult == 1 || linkResult == 1){ //if login is used
            return 1;
        } else if ((passwordVal = PasswordValidator.isValid(link.getPassword())) != 0){ //if password is valid
            return passwordVal;
        } else{ //then... try to send an e-mail
            UUID code = UUID.randomUUID();
            link.setActivationLink(code.toString());
            int state = EMAILSender.sendActivationLink(link);
            if (state == 0) { //if it goes fine, add link to the database
                activationLinkDAO.add(link);
            }
            return state;
        }
    }

    public int activate(String link){
        return activationLinkDAO.activate(link);
    }

}
