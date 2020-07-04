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

//    public StatusResponse addCode(String login, String code){
//        //initialize output data
//        StatusResponse response = new StatusResponse();
//        response.setStatus(-1);
//
//        //1st node
//        int userId = mapper.getId(code);
//        if (userId == -1){
//            return response;
//        }
//
//        String newCode = mapper.updateCode(code);
//        response.setCode(newCode);
//
//        //2nd and 3rd node
//        LoginCode loginCode = LoginCode.buildLoginCode(login);
//
//
//        //2nd
//        if (login.isEmpty() || login.isBlank()){
//            response.setStatus(1);
//            return response;
//        }
//
//        //4th node
//        if (isLoginOccupied(login)){
//            response.setStatus(-2);
//            return response;
//        }
//
//        User user = userDAO.getById(userId).orElse(null);
//
//        if (user == null || loginCode == null){ //login code is 2nd node
//            response.setCode("UNKNOWN USER");
//            return response;
//        }
//        loginCode.setUser(user);
//
//        int addingStatus = changeLoginDAO.addOrUpdateCode(loginCode);
//        if (addingStatus == 0){
//            //todo delete 0 and uncomment on the right
//            int emailStatus = 0;//EMAILSender.sendChangeLoginLink(loginCode.getCode(), user);
//            response.setStatus(emailStatus);
//        } else {
//            response.setStatus(addingStatus);
//        }
//        return response;
//    }

//    private boolean isLoginOccupied(String login){
//        return userDAO.exists(Example.of(new User(login, null)))
//                && activationLinkDAO.exists(Example.of(new ActivationLink(login)))
//                && changeLoginDAO.exists(Example.of(new LoginCode(login)));
//    }

    public StatusResponse activateCode(String loginCode, String code){
        ActivateLoginCodeModel model = new ActivateLoginCodeModel();
        model.LOGIN_CODE_STRING = loginCode;
        model.RESPONSE = new StatusResponse();
        model.CODE = code;
        updatingChain.executeDefaultOrdered(model);
        return model.RESPONSE;
    }

//    public StatusResponse activateCode(String loginCode, String code){
//        StatusResponse response = new StatusResponse();
//
//        int id = mapper.getId(code);
//
//        //validate user first
//        if (id == -1){
//            response.setCode("UNKNOWN CODE");
//            return response;
//        }
//
//        //if ok, generate code
//        String newCode = mapper.updateCode(code);
//        response.setCode(newCode);
//
//        LoginCode selectedCode = changeLoginDAO.getCode(loginCode).orElse(null);
//        if (selectedCode == null){
//            response.setStatus(-2);
//            return response;
//        }
//
//        response.setStatus(-3);
//        int result = changeLoginDAO.deleteLogin(selectedCode);
//        if (result == -1){
//            return response;
//        }
//
//        User existingUser = userDAO.getById(id).orElse(null);
//        if (existingUser == null){
//            return response;
//        }
//
//        User toUpdate = existingUser.cloneWithLogin(selectedCode.getLogin());
//        userDAO.update(id, toUpdate);
//
//        response.setStatus(0);
//        return response;
//    }

}
