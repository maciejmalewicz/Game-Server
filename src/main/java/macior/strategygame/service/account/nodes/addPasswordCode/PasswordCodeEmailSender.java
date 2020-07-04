package macior.strategygame.service.account.nodes.addPasswordCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.AddPasswordCodeModel;
import macior.strategygame.service.utilities.EMAILSender;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class PasswordCodeEmailSender extends ChainNode<AddPasswordCodeModel> {

    @Override
    public void execute(AddPasswordCodeModel model, ChainExecutor executor) {
        try {
            EMAILSender.sendChangePasswordLink(model.PASSWORD_CODE.getCode(), model.USER);
        } catch (MessagingException e) {
            e.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.EMAIL_SENDING_ERROR);
            executor.stop();
        }
    }
}
