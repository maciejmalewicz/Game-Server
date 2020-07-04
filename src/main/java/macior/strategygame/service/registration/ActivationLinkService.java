package macior.strategygame.service.registration;

import executionChains.Chain;
import macior.strategygame.dao.activationLinks.ActivationDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.ActivationLink;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.registration.codeAddingNodes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivationLinkService {

    @Autowired
    private ActivationDAO activationLinkDAO;

    @Autowired
    private UserDAO userDAO;

    private Chain<ActivationLinkAddingModel> addingChain;

    @Autowired
    public ActivationLinkService(
            ActivationCodeAddingRequestValidator requestValidator,
            ActivationCodeOccupiedDataValidator occupiedDataValidator,
            ActivationLinkBuilder linkBuilder,
            ActivationLinkAddingNode addingNode,
            ActivationLinkEmailSender emailSender
    ){
        addingChain = new Chain<>(
                requestValidator,
                occupiedDataValidator,
                linkBuilder,
                addingNode,
                emailSender
        );
    }

    public int addActivationLink(ActivationLink link){ //0 if all is fine, plus some errors :)
        ActivationLinkAddingModel model = new ActivationLinkAddingModel();
        model.LOGIN = link.getLogin();
        model.PASSWORD = link.getPassword();
        model.EMAIL = link.getEmail();
        model.RESPONSE = new StatusResponse();
        addingChain.executeDefaultOrdered(model);
        return model.RESPONSE.getStatus();
    }

    public int activate(String link){
        return activationLinkDAO.activateLink(link);
    }

}
