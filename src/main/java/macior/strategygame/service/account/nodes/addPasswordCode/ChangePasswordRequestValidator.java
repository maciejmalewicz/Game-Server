package macior.strategygame.service.account.nodes.addPasswordCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.AddPasswordCodeModel;
import macior.strategygame.service.utilities.PasswordValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ChangePasswordRequestValidator extends ChainNode<AddPasswordCodeModel> {

    @Override
    public void execute(AddPasswordCodeModel model, ChainExecutor executor) {
        String password = model.PASSWORD;
        if (password == null || password.isEmpty() || password.isBlank()){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
            return;
        }
        int validationResult = PasswordValidator.isValid(password);
        if (validationResult != 0){
            model.RESPONSE.setStatus(validationResult);
            executor.stop();
        }
    }
}
