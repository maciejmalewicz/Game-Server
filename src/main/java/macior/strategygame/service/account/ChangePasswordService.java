package macior.strategygame.service.account;

import executionChains.Chain;
import macior.strategygame.dao.account.ChangePasswordDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.account.models.ActivatePasswordCodeModel;
import macior.strategygame.service.account.models.AddPasswordCodeModel;
import macior.strategygame.service.account.nodes.UserGettingNode;
import macior.strategygame.service.account.nodes.addPasswordCode.ChangePasswordRequestValidator;
import macior.strategygame.service.account.nodes.addPasswordCode.PasswordCodeAddingNode;
import macior.strategygame.service.account.nodes.addPasswordCode.PasswordCodeBuilder;
import macior.strategygame.service.account.nodes.addPasswordCode.PasswordCodeEmailSender;
import macior.strategygame.service.account.nodes.updatePassword.UpdatePasswordDatabaseChanger;
import macior.strategygame.service.account.nodes.updatePassword.UpdatePasswordRequestValidator;
import macior.strategygame.service.playerCommandPipelines.nodes.CodeChangingNode;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("changePasswordDAO")
    private ChangePasswordDAO passwordDAO;

    private Chain<AddPasswordCodeModel> addingChain; //adds activation code

    private Chain<ActivatePasswordCodeModel> updatingChain; //activates code

    @Autowired
    public ChangePasswordService(
           CodeChangingNode codeChangingNode,
           ChangePasswordRequestValidator requestValidator,
           PasswordCodeBuilder passwordCodeBuilder,
           UserGettingNode userGettingNode,
           PasswordCodeAddingNode passwordCodeAddingNode,
           PasswordCodeEmailSender emailSender,

           UpdatePasswordRequestValidator updatePasswordRequestValidator,
           UpdatePasswordDatabaseChanger databaseChanger
    ){
        addingChain = new Chain<>(
                codeChangingNode,
                requestValidator,
                passwordCodeBuilder,
                userGettingNode,
                passwordCodeAddingNode,
                emailSender
        );

        updatingChain = new Chain<>(
                codeChangingNode,
                updatePasswordRequestValidator,
                databaseChanger
        );

    }

    public StatusResponse addCode(String password, String code){
        AddPasswordCodeModel model = new AddPasswordCodeModel();
        model.PASSWORD = password;
        model.CODE = code;
        model.RESPONSE = new StatusResponse();
        addingChain.executeDefaultOrdered(model);
        return model.RESPONSE; //todo: test it
    }

    public StatusResponse activateCode(String activationCode, String code){
        ActivatePasswordCodeModel model = new ActivatePasswordCodeModel();
        model.CODE = code;
        model.ACTIVATION_CODE = activationCode;
        model.RESPONSE = new StatusResponse();
        updatingChain.executeDefaultOrdered(model);
        return model.RESPONSE;
    }

}
