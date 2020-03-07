package macior.strategygame.service.utilities;

import macior.strategygame.models.NotificationResponse;
import macior.strategygame.models.ResponseBase;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import macior.strategygame.service.utilities.mapper.PlayersInbox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private PlayerGameMapperService mapper;

    public NotificationResponse notify(String code){
        int id = mapper.getId(code);
        NotificationResponse response = new NotificationResponse();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        PlayersInbox inbox = mapper.checkInbox(id);
        response.setInbox(inbox);
        return response;
    }
}
