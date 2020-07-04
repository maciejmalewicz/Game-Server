package macior.strategygame.service.account.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.User;
import macior.strategygame.service.account.models.UserConcernedModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGettingNode extends ChainNode<UserConcernedModel> {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public void execute(UserConcernedModel model, ChainExecutor executor) {
        Optional<User> optionalUser = userDAO.findById(model.ID);
        if (optionalUser.isEmpty()){
            model.RESPONSE.setStatus(MenuErrors.USER_NOT_FOUND);
            executor.stop();
            return;
        }
        model.USER = optionalUser.get();
    }
}
