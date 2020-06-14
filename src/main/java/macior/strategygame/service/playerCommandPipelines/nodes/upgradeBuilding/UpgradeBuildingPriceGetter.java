package macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingPriceGetter extends ChainNode<UpgradeBuildingModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        PlayersUpgradesSet upgrades = model.PLAYER.getUpgradesSet();

        ResourceSet basePrice = model.BUILDING_CONFIG.getCost(model.NEXT_LEVEL);

        if (upgrades.upgraded(Upgrades.ENGINEERING_PATTERNS)) {
            double discount = configuration.getUpgradesConfig().getImprovementUpgradesConfig()
                    .getEngineeringPatterns().COST_REDUCTION;
            discount = 1 - discount;
            basePrice.multiplyResources(discount);
        }

        model.PRICE = basePrice.canPurchase(model.PLAYER);
    }
}
