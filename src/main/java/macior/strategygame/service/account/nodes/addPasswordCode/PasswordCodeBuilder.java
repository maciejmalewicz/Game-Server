package macior.strategygame.service.account.nodes.addPasswordCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.account_management.PasswordCode;
import macior.strategygame.service.account.models.AddPasswordCodeModel;
import org.springframework.stereotype.Component;

@Component
public class PasswordCodeBuilder extends ChainNode<AddPasswordCodeModel> {

    @Override
    public void execute(AddPasswordCodeModel model, ChainExecutor executor) {
        model.PASSWORD_CODE = PasswordCode.buildPasswordCode(model.PASSWORD);
        model.PASSWORD_CODE.setId(model.ID);
    }
}
