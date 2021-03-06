package macior.strategygame.service.playerCommandPipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
import macior.strategygame.service.playerCommandPipelines.models.ArmyTrainingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingConfigurationGetter extends ChainNode<ArmyTrainingModel> {

    @Autowired
    private GameConfiguration gameConfiguration;

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        MechFactory factory = (MechFactory)model.FACTORY;
        ArmyTrainingRequest request = (ArmyTrainingRequest) model.REQUEST;

        model.FACTORY_CONFIG = getMechFactoryConfig(factory);
        model.MECH_CONFIG = getMechConfig(request);
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
