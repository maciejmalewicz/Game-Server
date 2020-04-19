package macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class NewBuildingsUpgradesValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildNewBuildingModel = (BuildNewBuildingModel)model;
        PlayersUpgradesSet upgrades = buildNewBuildingModel.PLAYER.getUpgradesSet();
        BuildingRequest request = ((BuildNewBuildingModel) model).REQUEST;
        if (request.getBuilding() == 6 && !upgrades.upgraded(Upgrades.TANKS)){
            model.RESPONSE.setStatus(GameErrors.TANKS_NOT_UPGRADED);
        }
        if (request.getBuilding() == 7 && !upgrades.upgraded(Upgrades.CANNONS)){
            model.RESPONSE.setStatus(GameErrors.CANNONS_NOT_UPGRADED);
        }
    }
}
