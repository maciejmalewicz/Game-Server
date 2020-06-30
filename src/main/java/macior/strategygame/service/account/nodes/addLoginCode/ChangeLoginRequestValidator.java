package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.ChangeLoginModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ChangeLoginRequestValidator extends ChainNode<ChangeLoginModel> {

    @Override
    public void execute(ChangeLoginModel model, ChainExecutor executor) {
        String login = model.LOGIN;
        if (login == null || login.isEmpty() || login.isBlank()){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
        }
    }
}
