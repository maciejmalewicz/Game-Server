package macior.strategygame.service.utilities;

import macior.strategygame.models.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private PlayerGameMapperService mapper;

    public ResponseBase notify(String code){
        int id = mapper.getId(code);
        ResponseBase response = new ResponseBase();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        return response;
    }
}
