package macior.strategygame.service.registration.codeAddingNodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.service.registration.ActivationLinkAddingModel;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ActivationLinkBuilder extends ChainNode<ActivationLinkAddingModel> {

    @Override
    public void execute(ActivationLinkAddingModel model, ChainExecutor executor) {
        ActivationLink activationLink = new ActivationLink();
        activationLink.setLogin(model.LOGIN);
        activationLink.setPassword(model.PASSWORD);
        activationLink.setEmail(model.EMAIL);
        activationLink.setActivationLink(UUID.randomUUID().toString());
        model.ACTIVATION_LINK = activationLink;
    }
}
