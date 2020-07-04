package macior.strategygame.service.account;

import executionChains.Chain;
import macior.strategygame.dao.account.ChangeLoginDAO;
import macior.strategygame.dao.activationLinks.IActivationLinkDAO;
import macior.strategygame.dao.users.IUserDAO;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.account.models.ActivateLoginCodeModel;
import macior.strategygame.service.account.models.AddLoginCodeModel;
import macior.strategygame.service.account.nodes.UserGettingNode;
import macior.strategygame.service.account.nodes.addLoginCode.*;
import macior.strategygame.service.account.nodes.updateLogin.UpdateLoginDatabaseChanger;
import macior.strategygame.service.account.nodes.updateLogin.UpdateLoginRequestValidator;
import macior.strategygame.service.playerCommandPipelines.nodes.CodeChangingNode;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangeLoginService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IActivationLinkDAO activationLinkDAO;

    @Autowired
    private ChangeLoginDAO changeLoginDAO;

    private Chain<AddLoginCodeModel> addingChain;

    private Chain<ActivateLoginCodeModel> updatingChain;

    @Autowired
    public ChangeLoginService(
            CodeChangingNode codeChangingNode,
            ChangeLoginRequestValidator requestValidator,
            ChangeLoginGetter changeLoginGetter,
            ChangeLoginOccupiedValidator occupiedValidator,
            UserGettingNode userGettingNode,
            ChangeLoginCodeAddingNode addingNode,
            ChangeLoginEmailSender emailSender,

            UpdateLoginRequestValidator updateLoginRequestValidator,
            UpdateLoginDatabaseChanger databaseChanger
    ){
        addingChain = new Chain<>(
                codeChangingNode,
                requestValidator,
                changeLoginGetter,
                occupiedValidator,
                userGettingNode,
                addingNode,
                emailSender
        );

        updatingChain = new Chain<>(
                codeChangingNode,
                updateLoginRequestValidator,
                databaseChanger
        );
    }

    public StatusResponse addCode(String login, String code){
        //initialize output data
        System.out.println("CHANGE LOGIN");
        AddLoginCodeModel model = new AddLoginCodeModel();
        model.CODE = code;
        model.LOGIN = login;
        model.RESPONSE = new StatusResponse();
        addingChain.executeDefaultOrdered(model);
        return model.RESPONSE;
    }


    public StatusResponse activateCode(String loginCode, String code){
        ActivateLoginCodeModel model = new ActivateLoginCodeModel();
        model.LOGIN_CODE_STRING = loginCode;
        model.RESPONSE = new StatusResponse();
        model.CODE = code;
        updatingChain.executeDefaultOrdered(model);
        return model.RESPONSE;
    }

}
