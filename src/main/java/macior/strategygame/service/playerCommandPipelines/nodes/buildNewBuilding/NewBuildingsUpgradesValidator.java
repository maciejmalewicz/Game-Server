package macior.strategygame.service.playerCommandPipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.playerCommandPipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class NewBuildingsUpgradesValidator extends ChainNode<BuildNewBuildingModel> {

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        PlayersUpgradesSet upgrades = model.PLAYER.getUpgradesSet();
        BuildingRequest request = (BuildingRequest) model.REQUEST;
        if (request.getBuilding() == 6 && !upgrades.upgraded(Upgrades.TANKS)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.TANKS_NOT_UPGRADED);
            return;
        }
        if (request.getBuilding() == 7 && !upgrades.upgraded(Upgrades.CANNONS)){
            model.RESPONSE.setStatus(GameErrors.CANNONS_NOT_UPGRADED);
            executor.stop();
        }
    }
}
