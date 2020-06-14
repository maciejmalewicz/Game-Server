package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.game.BattlesManagement.configuration.MechConfig;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.CannonFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.DroidFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.MechFactory;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.TankFactory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;
import macior.strategygame.models.game.configuration.ArmyBalanceConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingConfigurationGetter extends Node {

    @Autowired
    private GameConfiguration gameConfiguration;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        MechFactory factory = (MechFactory)trainingModel.FACTORY;
        ArmyTrainingRequest request = (ArmyTrainingRequest) trainingModel.REQUEST;

        trainingModel.FACTORY_CONFIG = getMechFactoryConfig(factory);
        trainingModel.MECH_CONFIG = getMechConfig(request);
    }

    private MechFactoryConfig getMechFactoryConfig(MechFactory factory){
        SmallBuildingsConfig config = gameConfiguration.getSmallBuildingsConfig();

        if (factory.getClass() == DroidFactory.class){
            return config.getDroidFactoryConfig();
        } else if (factory.getClass() == TankFactory.class){
            return config.getTankFactoryConfig();
        } else if (factory.getClass() == CannonFactory.class){
            return config.getCannonFactoryConfig();
        }
        return null;
    }

    private MechConfig getMechConfig(ArmyTrainingRequest request){
        ArmyBalanceConfig config = gameConfiguration.getArmyBalanceConfig();
        if (request.unitType == 1){
            return config.getDroidsConfig();
        } else if (request.unitType == 2){
            return config.getTanksConfig();
        } else if (request.unitType == 3){
            return config.getCannonsConfig();
        }
        return null;
    }


}
