package macior.strategygame.service.registration.codeAddingNodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.dao.activationLinks.IActivationLinkDAO;
import macior.strategygame.service.registration.ActivationLinkAddingModel;
import macior.strategygame.service.utilities.errors.MenuErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ActivationLinkAddingNode extends ChainNode<ActivationLinkAddingModel> {

    @Autowired
    private IActivationLinkDAO activationLinkDAO;

    @Transactional
    @Override
    public void execute(ActivationLinkAddingModel model, ChainExecutor executor) {
        try {
            activationLinkDAO.save(model.ACTIVATION_LINK);
        } catch (Exception exc){
            model.RESPONSE.setStatus(MenuErrors.INTERNAL_ERROR);
            executor.stop();
        }
    }
}
