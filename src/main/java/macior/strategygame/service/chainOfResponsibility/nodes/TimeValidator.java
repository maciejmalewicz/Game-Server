package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class TimeValidator extends Node{

    private static int MAX_TIME = 3600;

    @Override
    public void applyChanges(ChainModel model) {
        PlayerChangesModel buildingModel = (PlayerChangesModel) model;
        if (MAX_TIME < buildingModel.FINISHING_TIME){
            buildingModel.RESPONSE.setStatus(GameErrors.NOT_ENOUGH_TIME);
        }
    }
}
