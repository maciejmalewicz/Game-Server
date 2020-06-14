package macior.strategygame.service.pipelines.nodes.upgradeBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallFactoryConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingLevelValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        BuildingConfig config = upgradeModel.BUILDING_CONFIG;
        Player player = upgradeModel.PLAYER;

        int currentMaxLevel = getCurrentMaxLevel(config, player.getUpgradesSet());
        if (upgradeModel.NEXT_LEVEL > currentMaxLevel){
            upgradeModel.RESPONSE.setStatus(GameErrors.LEVEL_TOO_HIGH);
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
