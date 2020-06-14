package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ArmyUpgrades.AdvancedDroids;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.ArmyQuantityGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingPriceGetter extends Node {

    @Autowired
    private GameConfiguration gameConfiguration;

    @Autowired
    private ArmyQuantityGetter quantityGetter;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        ArmyTrainingRequest request = (ArmyTrainingRequest)trainingModel.REQUEST;

        int singleMechCost = getSingleMechCost(request);
        int costQuantityMultiplier = quantityGetter.getProductionCostQuantity(trainingModel.FACTORY_CONFIG, request);
        int totalCost = singleMechCost*costQuantityMultiplier;
        //applying perks from upgrades (advanced droids)
        totalCost = applyUpgradesDiscounts(trainingModel.PLAYER.getUpgradesSet(), request, totalCost);

        ResourceSet resources = new ResourceSet(totalCost, 0 ,0);
        trainingModel.PRICE = resources.canPurchase(trainingModel.PLAYER);
    }

    private int getSingleMechCost(ArmyTrainingRequest request){
        if (request.unitType == 1){
            return gameConfiguration.getArmyBalanceConfig().getDroidsConfig().METAL_COST;
        }
        if (request.unitType == 2){
            return gameConfiguration.getArmyBalanceConfig().getTanksConfig().METAL_COST;
        }
        if (request.unitType == 3){
            return gameConfiguration.getArmyBalanceConfig().getCannonsConfig().METAL_COST;
        }
        return 10000;
    }

    private int applyUpgradesDiscounts(PlayersUpgradesSet upgrades, ArmyTrainingRequest request, int price){
        if (request.unitType != 1){
            return price;
        }

        AdvancedDroids upgradeConfig = gameConfiguration.getUpgradesConfig().getArmyUpgradesConfig().getAdvancedDroids();
        if (upgrades.upgraded(Upgrades.ADVANCED_DROIDS)){
            price = (int)(price*(1-upgradeConfig.PRODUCTION_COST_REDUCTION));
        }

        return price;
    }
}
