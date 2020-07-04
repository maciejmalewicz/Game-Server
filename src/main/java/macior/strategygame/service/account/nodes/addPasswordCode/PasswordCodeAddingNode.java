package macior.strategygame.service.account.nodes.addPasswordCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.account.ChangePasswordDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.account_management.PasswordCode;
import macior.strategygame.service.account.models.AddPasswordCodeModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class PasswordCodeAddingNode extends ChainNode<AddPasswordCodeModel> {

    @Autowired
    private ChangePasswordDAO changePasswordDAO;

    @Autowired
    private IUserDAO userDAO;

    @Transactional
    @Override
    public void execute(AddPasswordCodeModel model, ChainExecutor executor) {
        try {
            addOrUpdatePasswordCode(model);
        } catch (Exception exc){
            exc.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }

    private void addOrUpdatePasswordCode(AddPasswordCodeModel model) throws Exception {
        Optional<PasswordCode> optional = changePasswordDAO.findById(model.ID);
        if (optional.isEmpty()){
            changePasswordDAO.save(model.PASSWORD_CODE);
            return;
        }
        PasswordCode code = optional.get();
        code.setPassword(model.PASSWORD_CODE.getPassword());
        code.setCode(model.PASSWORD_CODE.getCode());
        changePasswordDAO.save(code);
    }
}
