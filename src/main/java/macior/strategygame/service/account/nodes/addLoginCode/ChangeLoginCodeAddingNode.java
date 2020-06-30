package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.account.ChangeLoginDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.account.models.ChangeLoginModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class ChangeLoginCodeAddingNode extends ChainNode<ChangeLoginModel> {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private ChangeLoginDAO changeLoginDAO;

    @Transactional
    @Override
    public void execute(ChangeLoginModel model, ChainExecutor executor) {
        Optional<User> optionalUser = userDAO.findById(model.ID);
        if (optionalUser.isEmpty()){
            model.RESPONSE.setStatus(MenuErrors.USER_NOT_FOUND);
            executor.stop();
            return;
        }
        model.USER = optionalUser.get();

        model.LOGIN_CODE.setId(model.ID);
        try {
            addOrUpdateLoginCode(model);
        } catch (Exception exc){
            exc.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }

    private void addOrUpdateLoginCode(ChangeLoginModel model) throws Exception {
        Optional<LoginCode> optional = changeLoginDAO.findById(model.ID);
        if (optional.isEmpty()){
            changeLoginDAO.save(model.LOGIN_CODE);
            return;
        }
        LoginCode code = optional.get();
        code.setLogin(model.LOGIN_CODE.getLogin());
        code.setCode(model.LOGIN_CODE.getCode());
        changeLoginDAO.save(code);
    }


}
