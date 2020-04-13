package macior.strategygame.service;

import macior.strategygame.models.ResponseBase;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationService {

    @Autowired
    private PlayerGameMapperService mapper;

    //validates, but also updates the code
    //for encapsulation of what we do every time
    //returns players id, so it can be used later
    //in general, after invoking this function we shouldn't care about code and we should have id
    //of a user
    public int validateAndGetId(String code, ResponseBase toChange){
        int id = mapper.getId(code);
        if (id == -1){
            toChange.setCode("UNKNOWN CODE");
            return id;
        }
        String newCode = mapper.updateCode(code);
        toChange.setCode(newCode);
        return id;
    }
}
