package macior.strategygame.service.account;

import macior.strategygame.dao.account.ChangePasswordDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.PasswordCode;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.utilities.PasswordValidator;
import macior.strategygame.service.utilities.PlayerGameMapperService;
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

    public StatusResponse addCode(String password, String code){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        User user = userDAO.getById(id).orElse(null);
        if (user == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        int valid = PasswordValidator.isValid(password);

        if (valid != 0){
            response.setStatus(valid);
            return response;
        }
        PasswordCode passwordCode = PasswordCode.buildPasswordCode(password);
        passwordCode.setUser(user);

        int result = passwordDAO.addOrUpdateCode(passwordCode);

        if (result != 0){
            response.setStatus(result);
            return response;
        }

        //todo uncomment email sender
        result = 0; //EMAILSender.sendChangePasswordLink(passwordCode.getCode(), user);
        response.setStatus(result);

        return response;
    }

    public StatusResponse activateCode(String activationCode, String code){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        response.setCode("UNKNOWN CODE");
        if (id == -1){
            return response;
        }
        User user = userDAO.getById(id).orElse(null);
        if (user == null){
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        PasswordCode passwordCode = passwordDAO.getCode(activationCode).orElse(null);
        if (passwordCode == null){
            response.setStatus(-2);
            return response;
        }

        int result = passwordDAO.deletePassword(passwordCode);
        if (result != 0){
            response.setStatus(-3);
            return response;
        }

        User toUpdate = user.cloneWithPassword(passwordCode.getPassword());
        userDAO.update(id, toUpdate);

        response.setStatus(0);
        return response;
    }

}
