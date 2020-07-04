package macior.strategygame.service.registration.codeAddingNodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.registration.ActivationLinkAddingModel;
import macior.strategygame.service.utilities.EMAILSender;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class ActivationLinkEmailSender extends ChainNode<ActivationLinkAddingModel> {

    @Override
    public void execute(ActivationLinkAddingModel model, ChainExecutor executor) {
        try {
            EMAILSender.sendActivationLink(model.ACTIVATION_LINK);
        } catch (MessagingException e) {
            e.printStackTrace();
            model.RESPONSE.setStatus(MenuErrors.EMAIL_SENDING_ERROR);
            executor.stop();
        }
    }
}
