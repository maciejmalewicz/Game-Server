package macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallFactoryConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingLevelValidator extends ChainNode<UpgradeBuildingModel> {

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        BuildingConfig config = model.BUILDING_CONFIG;
        Player player = model.PLAYER;

        int currentMaxLevel = getCurrentMaxLevel(config, player.getUpgradesSet());
        if (model.NEXT_LEVEL > currentMaxLevel){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.LEVEL_TOO_HIGH);
        }
    }

    private int getCurrentMaxLevel(BuildingConfig config, PlayersUpgradesSet upgrades){
        int level = config.MAX_LEVEL;
        if (!(config instanceof SmallFactoryConfig)){
            return level;
        }
        //handle small factories
        if (!upgrades.upgraded(Upgrades.SPACE_MANAGEMENT)){ //space management not upgraded
            return --level;
        }
        return level;
    }


}
