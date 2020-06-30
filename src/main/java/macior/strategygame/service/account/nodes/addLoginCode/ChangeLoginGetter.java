package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.account.models.ChangeLoginModel;
import org.springframework.stereotype.Component;

@Component
public class ChangeLoginGetter extends ChainNode<ChangeLoginModel> {

    @Override
    public void execute(ChangeLoginModel model, ChainExecutor executor) {
        model.LOGIN_CODE = LoginCode.buildLoginCode(model.LOGIN);
    }
}
