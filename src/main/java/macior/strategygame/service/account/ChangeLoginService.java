package macior.strategygame.service.account;

import macior.strategygame.dao.account.ChangeLoginDAO;
import macior.strategygame.dao.activationLinks.ActivationLinkDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.account_management.LoginCode;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ChangeLoginService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("activationLinkDAO")
    private ActivationLinkDAO activationLinkDAO;

    @Autowired
    @Qualifier("changeLoginDAO")
    private ChangeLoginDAO changeLoginDAO;

    public StatusResponse addCode(String login, String code){
        //initialize output data
        int playerId = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        LoginCode loginCode = LoginCode.buildLoginCode(login);

        if (playerId == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        User user = userDAO.getById(playerId).orElse(null);

        if (user == null || loginCode == null){
            response.setCode("UNKNOWN USER");
            return response;
        }
        loginCode.setUser(user);

        if (login.isEmpty() || login.isBlank()){
            response.setStatus(1);
            return response;
        }

        if (isLoginOccupied(login)){
            response.setStatus(-2);
            return response;
        }

        int addingStatus = changeLoginDAO.addOrUpdateCode(loginCode);
        if (addingStatus == 0){
            //todo delete 0 and uncomment on the right
            int emailStatus = 0;//EMAILSender.sendChangeLoginLink(loginCode.getCode(), user);
            response.setStatus(emailStatus);
        } else {
            response.setStatus(addingStatus);
        }
        return response;
    }

    private boolean isLoginOccupied(String login){
        return userDAO.existsLogin(login)
                + activationLinkDAO.existsLogin(login)
                + changeLoginDAO.existsLogin(login) != 0;
    }

    public StatusResponse activateCode(String loginCode, String code){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);

        //validate user first
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        //if ok, generate code
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        LoginCode selectedCode = changeLoginDAO.getCode(loginCode).orElse(null);
        if (selectedCode == null){
            response.setStatus(-2);
            return response;
        }

        response.setStatus(-3);
        int result = changeLoginDAO.deleteLogin(selectedCode);
        if (result == -1){
            return response;
        }

        User existingUser = userDAO.getById(id).orElse(null);
        if (existingUser == null){
            return response;
        }

        User toUpdate = existingUser.cloneWithLogin(selectedCode.getLogin());
        userDAO.update(id, toUpdate);

        response.setStatus(0);
        return response;
    }

}
