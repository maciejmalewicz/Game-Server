package macior.strategygame.service.registration.codeAddingNodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.registration.ActivationLinkAddingModel;
import macior.strategygame.service.utilities.PasswordValidator;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.stereotype.Component;

@Component
public class ActivationCodeAddingRequestValidator extends ChainNode<ActivationLinkAddingModel> {

    @Override
    public void execute(ActivationLinkAddingModel model, ChainExecutor executor) {

        if (isStringEmpty(model.LOGIN)){
            executor.stop();
            model.RESPONSE.setStatus(MenuErrors.EMPTY_LOGIN);
            return;
        }

        if (isStringEmpty(model.EMAIL)){
            executor.stop();
            model.RESPONSE.setStatus(MenuErrors.EMPTY_EMAIL);
            return;
        }

        if (isStringEmpty(model.PASSWORD)){
            executor.stop();
            model.RESPONSE.setStatus(MenuErrors.EMPTY_PASSWORD);
            return;
        }

        int passwordValue = PasswordValidator.isValid(model.PASSWORD);
        if (passwordValue != 0){
            executor.stop();
            model.RESPONSE.setStatus(passwordValue);
        }

    }

    private boolean isStringEmpty(String str){
        return str == null || str.isEmpty() || str.isBlank();
    }
}
