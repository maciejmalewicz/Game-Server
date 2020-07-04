package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.account.models.AddLoginCodeModel;
import org.springframework.stereotype.Component;

@Component
public class ChangeLoginGetter extends ChainNode<AddLoginCodeModel> {

    @Override
    public void execute(AddLoginCodeModel model, ChainExecutor executor) {
        model.LOGIN_CODE = LoginCode.buildLoginCode(model.LOGIN);
        model.LOGIN_CODE.setId(model.ID);
    }
}
