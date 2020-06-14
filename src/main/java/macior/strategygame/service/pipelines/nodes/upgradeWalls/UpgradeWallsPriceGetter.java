package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.ImprovementUpgradesConfig;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsPriceGetter extends Node {

    @Autowired
    private WallsConfig wallsConfig;

    @Autowired
    private ImprovementUpgradesConfig improvementUpgradesConfig;

    @Override
    public void applyChanges(ChainModel model){
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        ResourceSet price = wallsConfig.getCost(wallsModel.NEXT_LEVEL);
        Player buyer = wallsModel.PLAYER;

        if (buyer.getUpgradesSet().upgraded(Upgrades.ENGINEERING_PATTERNS)){
            price.multiplyResources(1 - improvementUpgradesConfig.getEngineeringPatterns().COST_REDUCTION);

        }
        wallsModel.PRICE = price.canPurchase(buyer);
    }
}
