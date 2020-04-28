package macior.strategygame.service.chainOfResponsibility.nodes.armyTraining;

import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.CannonFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.DroidFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.MechFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTrainingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.ArmyQuantityGetter;
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
}
