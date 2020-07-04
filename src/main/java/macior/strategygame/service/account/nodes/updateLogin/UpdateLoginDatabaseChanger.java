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
import org.springframework.data.domain.Example;
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
        model.LOGIN_CODE = model.USER.getLoginCode();

        if (!isCodeCorrect(model.LOGIN_CODE, model.LOGIN_CODE_STRING)){
            model.RESPONSE.setStatus(MenuErrors.CODE_NOT_FOUND);
            executor.stop();
            return;
        }

        model.LOGIN = model.LOGIN_CODE.getLogin();
        model.USER.setLoginCode(null); //necessary to delete login code.

        try {
            model.USER.setLogin(model.LOGIN);
            userDAO.save(model.USER);
            changeLoginDAO.delete(model.LOGIN_CODE);
        } catch (Exception exc){
            exc.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }

    private boolean isCodeCorrect(LoginCode loginCode, String selectedCode){
        if (loginCode == null){
            return false;
        }
        return loginCode.getCode().equals(selectedCode);
    }

    private Optional<LoginCode> getLoginCode(int id, String activationCode){
        LoginCode loginCode = new LoginCode();
        loginCode.setCode(activationCode);
        loginCode.setId(id);
        Example<LoginCode> example = Example.of(loginCode);
        return changeLoginDAO.findOne(example);
    }

}
