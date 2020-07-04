package macior.strategygame.service.account.nodes.updatePassword;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.ActivatePasswordCodeModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpdatePasswordRequestValidator extends ChainNode<ActivatePasswordCodeModel> {

    @Override
    public void execute(ActivatePasswordCodeModel model, ChainExecutor executor) {
        String code = model.ACTIVATION_CODE;
        if (code == null || code.isEmpty() || code.isBlank()){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
        }
    }
}
