package macior.strategygame.service.account.nodes.updatePassword;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.account.ChangePasswordDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.PasswordCode;
import macior.strategygame.service.account.models.ActivatePasswordCodeModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class UpdatePasswordDatabaseChanger extends ChainNode<ActivatePasswordCodeModel> {

    @Autowired
    private ChangePasswordDAO changePasswordDAO;

    @Autowired
    private IUserDAO userDAO;

    @Transactional
    @Override
    public void execute(ActivatePasswordCodeModel model, ChainExecutor executor) {
        Optional<User> optionalUser = userDAO.findById(model.ID);
        if (optionalUser.isEmpty()){
            model.RESPONSE.setStatus(MenuErrors.USER_NOT_FOUND);
            executor.stop();
            return;
        }
        model.USER = optionalUser.get();
        PasswordCode passwordCode = model.USER.getPasswordCode();


        if (!isPasswordCodeCorrect(passwordCode, model.ACTIVATION_CODE)){
            model.RESPONSE.setStatus(MenuErrors.CODE_NOT_FOUND);
            executor.stop();
            return;
        }

        //this is just to detach code from the user, so it's deleted
        model.USER.setPasswordCode(null); //important! won't work without it

        try {
            model.USER.setPassword(passwordCode.getPassword());
            userDAO.save(model.USER);
            changePasswordDAO.delete(passwordCode);
        } catch (Exception exc){
            exc.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }

    private boolean isPasswordCodeCorrect(PasswordCode passwordCode, String selectedCode){
        if (passwordCode == null){
            return false;
        }
        return passwordCode.getCode().equals(selectedCode);
    }

}
