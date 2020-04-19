package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class PaymentExecutor extends Node {

    //send an error if player can't afford it
    @Override
    public void applyChanges(ChainModel model){
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        if (buildingModel.PRICE == null){
            buildingModel.RESPONSE.setStatus(GameErrors.NOT_ENOUGH_RESOURCES);
        }
        buildingModel.PLAYER.getResources().substractResources(buildingModel.PRICE);
    }
}