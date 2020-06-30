package macior.strategygame.service.account.nodes.updateLogin;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.ActivateLoginCodeModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpdateLoginRequestValidator extends ChainNode<ActivateLoginCodeModel> {

    @Override
    public void execute(ActivateLoginCodeModel model, ChainExecutor executor) {
        String code = model.LOGIN_CODE_STRING;
        if (code == null || code.isEmpty() || code.isBlank()){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
        }
    }
}
