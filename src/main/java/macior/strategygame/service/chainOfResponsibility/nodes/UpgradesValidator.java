package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import org.springframework.stereotype.Component;

@Component
public class UpgradesValidator extends Node {

    @Override
    protected Node getNext(ChainModel model) {
        return null;
    }

    @Override
    protected void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildNewBuildingModel = (BuildNewBuildingModel)model;
        buildNewBuildingModel.UPGRADES = buildNewBuildingModel.PLAYER.getUpgradesSet();

    }

    //todo
    private void validateUpgradesForBuilding(){
        if (request.getBuilding() == 6){
            return upgrades.upgraded(Upgrades.TANKS);
        }
        if (request.getBuilding() == 7){
            return upgrades.upgraded(Upgrades.CANNONS);
        }
        return true;
    }
}
