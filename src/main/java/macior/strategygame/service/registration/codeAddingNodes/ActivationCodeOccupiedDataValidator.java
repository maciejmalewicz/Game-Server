package macior.strategygame.service.registration.codeAddingNodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.activationLinks.IActivationLinkDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.User;
import macior.strategygame.service.registration.ActivationLinkAddingModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

//checks if login or email is occupied
@Component
public class ActivationCodeOccupiedDataValidator extends ChainNode<ActivationLinkAddingModel> {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IActivationLinkDAO activationLinkDAO;

    @Override
    public void execute(ActivationLinkAddingModel model, ChainExecutor executor) {
        Optional<User> optionalUser = getUser(model.LOGIN, model.EMAIL);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            int error = getUserError(user, model);

            model.RESPONSE.setStatus(error);
            executor.stop();
            return;
        }

        Optional<ActivationLink> optionalLink = getActivationLink(model.LOGIN, model.EMAIL);
        if (optionalLink.isPresent()){
            ActivationLink link = optionalLink.get();
            int error = getActivationlinkError(link, model);

            model.RESPONSE.setStatus(error);
            executor.stop();
        }

    }

    private int getActivationlinkError(ActivationLink link, ActivationLinkAddingModel model){
        if (link.getEmail().equals(model.EMAIL)){
            return MenuErrors.EMAIL_OCCUPIED;
        }
        if (link.getLogin().equals(model.LOGIN)){
            return MenuErrors.LOGIN_OCCUPIED;
        }
        return MenuErrors.INTERNAL_ERROR;
    }

    private Optional<ActivationLink> getActivationLink(String login, String email){
        ActivationLink activationLink = new ActivationLink();
        activationLink.setLogin(login);
        activationLink.setEmail(email);
        Example<ActivationLink> example = Example.of(activationLink);
        return activationLinkDAO.findOne(example);
    }


    private int getUserError(User user, ActivationLinkAddingModel model){
        if (user.getEmail().equals(model.EMAIL)){
            return MenuErrors.EMAIL_OCCUPIED;
        }
        if (user.getLogin().equals(model.LOGIN)){
            return MenuErrors.LOGIN_OCCUPIED;
        }
        return MenuErrors.INTERNAL_ERROR;
    }

    private Optional<User> getUser(String login, String email){
        User user = new User();
        user.setLogin(login);
        user.setEmail(email);
        Example<User> example = Example.of(user);
        return userDAO.findOne(example);
    }
}
