package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeChangingNode extends Node{

    @Autowired
    private PlayerGameMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        int id = mapper.getId(model.CODE);
        if (id == -1){
            model.RESPONSE.setStatus(GameErrors.ACCESS_DENIED);
            model.RESPONSE.setCode("UNKNOWN CODE");
        }
        String newCode = mapper.updateCode(model.CODE);
        model.RESPONSE.setCode(newCode);
        model.ID = id;
    }
}
