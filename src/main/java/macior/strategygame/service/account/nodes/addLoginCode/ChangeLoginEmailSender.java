package macior.strategygame.service.account.nodes.addLoginCode;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.account.models.AddLoginCodeModel;
import macior.strategygame.service.utilities.EMAILSender;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ChangeLoginEmailSender extends ChainNode<AddLoginCodeModel> {

    @Override
    public void execute(AddLoginCodeModel model, ChainExecutor executor) {
        try {
            EMAILSender.sendChangeLoginLink(model.LOGIN_CODE.getCode(), model.USER);
        } catch (MessagingException e) {
            e.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.EMAIL_SENDING_ERROR);
            executor.stop();
        }
    }
}
