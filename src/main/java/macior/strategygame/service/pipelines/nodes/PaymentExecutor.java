package macior.strategygame.service.pipelines.nodes;

import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class PaymentExecutor extends Node {

    //send an error if player can't afford it
    @Override
    public void applyChanges(ChainModel model){
        PlayerChangesModel buildingModel = (PlayerChangesModel) model;
        if (buildingModel.PRICE == null){
            buildingModel.RESPONSE.setStatus(GameErrors.NOT_ENOUGH_RESOURCES);
        } else {
            buildingModel.PLAYER.getResources().substractResources(buildingModel.PRICE);
        }
    }
}
