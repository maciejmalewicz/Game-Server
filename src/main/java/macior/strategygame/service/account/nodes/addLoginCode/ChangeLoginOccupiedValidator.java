package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.account.ChangeLoginDAO;
import macior.strategygame.dao.activationLinks.IActivationLinkDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.account.models.ChangeLoginModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class ChangeLoginOccupiedValidator extends ChainNode<ChangeLoginModel> {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IActivationLinkDAO activationLinkDAO;

    @Autowired
    private ChangeLoginDAO changeLoginDAO;

    @Override
    public void execute(ChangeLoginModel model, ChainExecutor executor) {
        if (isLoginOccupied(model.LOGIN)){
            model.RESPONSE.setStatus(MenuErrors.LOGIN_OCCUPIED);
            executor.stop();
        }
    }

    private boolean isLoginOccupied(String login){
        return userDAO.exists(Example.of(new User(login)))
                || activationLinkDAO.exists(Example.of(new ActivationLink(login)))
                || changeLoginDAO.exists(Example.of(new LoginCode(login)));
    }
}
