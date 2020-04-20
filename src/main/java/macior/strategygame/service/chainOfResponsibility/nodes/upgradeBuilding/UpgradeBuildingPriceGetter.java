package macior.strategygame.service.chainOfResponsibility.nodes.upgradeBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingPriceGetter extends Node {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        PlayersUpgradesSet upgrades = upgradeModel.PLAYER.getUpgradesSet();

        ResourceSet basePrice = upgradeModel.BUILDING_CONFIG.getCost(upgradeModel.NEXT_LEVEL);

        if (upgrades.upgraded(Upgrades.ENGINEERING_PATTERNS)) {
            double discount = configuration.getUpgradesConfig().getImprovementUpgradesConfig()
                    .getEngineeringPatterns().COST_REDUCTION;
            discount = 1 - discount;
            basePrice.multiplyResources(discount);
        }

        upgradeModel.PRICE = basePrice.canPurchase(upgradeModel.PLAYER);
    }
}
