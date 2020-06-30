package macior.strategygame.service.account.nodes.updateLogin;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.account.ChangeLoginDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.account.models.ActivateLoginCodeModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class UpdateLoginDatabaseChanger extends ChainNode<ActivateLoginCodeModel> {
    //removes login code and changes login in db (during single transaction)

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ChangeLoginDAO changeLoginDAO;

    @Transactional
    @Override
    public void execute(ActivateLoginCodeModel model, ChainExecutor executor) {
        Optional<User> optionalUser = userDAO.findById(model.ID);
        if (optionalUser.isEmpty()){
            model.RESPONSE.setStatus(MenuErrors.USER_NOT_FOUND);
            executor.stop();
            return;
        }
        model.USER = optionalUser.get();

        Optional<LoginCode> loginCode = changeLoginDAO.findById(model.ID);
        if (loginCode.isEmpty()){
            model.RESPONSE.setStatus(MenuErrors.USER_NOT_FOUND);
            executor.stop();
            return;
        }

        model.LOGIN_CODE = loginCode.get();
        model.LOGIN = model.LOGIN_CODE.getLogin();

        try {
            changeLoginDAO.delete(model.LOGIN_CODE);
            changeLoginDAO.flush();
            model.USER.setLogin(model.LOGIN);
            userDAO.save(model.USER);
        } catch (Exception exc){
            exc.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }

}
