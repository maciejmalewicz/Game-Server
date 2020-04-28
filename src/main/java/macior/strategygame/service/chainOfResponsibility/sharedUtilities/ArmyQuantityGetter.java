package macior.strategygame.service.chainOfResponsibility.sharedUtilities;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import org.springframework.stereotype.Component;

@Component
public class ArmyQuantityGetter {

    public int getProductionCostQuantity(MechFactoryConfig factoryConfig, ArmyTrainingRequest request){
        int productionType = request.productionType;
        if (productionType == 1){
            return factoryConfig.LEVEL1_REGULAR_PRODUCTION;
        } else if (productionType == 2){
            return factoryConfig.LEVEL1_BIG_PRODUCTION;
        } else {
            return factoryConfig.LEVEL1_MASS_PRODUCTION;
        }
    }
}
