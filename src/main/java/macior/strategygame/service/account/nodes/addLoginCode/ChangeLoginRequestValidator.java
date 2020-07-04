package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.AddLoginCodeModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ChangeLoginRequestValidator extends ChainNode<AddLoginCodeModel> {

    @Override
    public void execute(AddLoginCodeModel model, ChainExecutor executor) {
        String login = model.LOGIN;
        if (login == null || login.isEmpty() || login.isBlank()){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
        }
    }
}
