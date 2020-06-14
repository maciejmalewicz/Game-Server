package macior.strategygame.service.playerCommandPipelines.sharedUtilities;

import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.MechFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.CannonFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.DroidFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.TankFactoryConfig;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import org.springframework.stereotype.Component;

@Component
public class ArmyQuantityGetter {

    public int getProductionCostQuantity(MechFactoryConfig factoryConfig, ArmyTrainingRequest request){
        int productionType = request.productionType;
        return factoryConfig.getQuantity(productionType, 1);
    }

    public int getProductionQuantity(MechFactoryConfig factoryConfig, MechFactory factory, int productionType){
        int level = factory.LEVEL;
        if (factoryConfig.getClass() == DroidFactoryConfig.class){
            DroidFactoryConfig config = (DroidFactoryConfig)factoryConfig;
            return config.getQuantity(productionType, level);
        } else if (factoryConfig.getClass() == TankFactoryConfig.class){
            TankFactoryConfig config = (TankFactoryConfig)factoryConfig;
            return config.getQuantity(productionType, level);
        } else if (factoryConfig.getClass() == CannonFactoryConfig.class){
            CannonFactoryConfig config = (CannonFactoryConfig)factoryConfig;
            return config.getQuantity(productionType, level);
        }
        return 0;
    }
}
