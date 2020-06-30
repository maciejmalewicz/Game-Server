package macior.strategygame.service;

import macior.strategygame.dao.activationLinks.ActivationLinkDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.service.utilities.EMAILSender;
import macior.strategygame.service.utilities.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
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



    public int addActivationLink(ActivationLink link){ //0 if all is fine, plus some errors :)
        System.out.println("serviced: " + link);
        String login = link.getLogin();
        String email = link.getEmail();
        int passwordVal;

        //login is empty
        if (login == null || login.isEmpty() || login.isBlank()){
            return 1;
        }

        //email is empty
        if (email == null || email.isBlank() || email.isEmpty()){
            return 7;
        }

        String password = link.getPassword();
        //password is empty so it's too short
        if (password == null){
            return 2;
        }

        //handling password errors from 2 to 5
        passwordVal = PasswordValidator.isValid(password);
        if (passwordVal != 0){
            return passwordVal;
        }

        //now i access database... not earlier in order to relax server
        int userResult = userDAO.existsInUsers(login, email);
        int linkResult = activationLinkDAO.existsInLinks(login, email);

        //if email is already used
        if (userResult == 2 || linkResult == 2){
            return 8;
        }

        //if login is already user
        if (userResult == 1 || linkResult == 1){
            return 9;
        }

        //finally, I try send an email
        UUID code = UUID.randomUUID();
        link.setActivationLink(code.toString());
        int state = 0;
        try {
            state = EMAILSender.sendActivationLink(link);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        //if I fail then report an error
        if (state != 0){
            System.out.println("Problemz sending. Returning 10!");
            return 10;
        }

        //if everything goes fine, add link to the database and return 0
        activationLinkDAO.add(link);
        return 0;
    }

    public int activate(String link){
        return activationLinkDAO.activate(link);
    }

}
