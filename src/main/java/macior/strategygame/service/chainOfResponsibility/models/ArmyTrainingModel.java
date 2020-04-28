package macior.strategygame.service.chainOfResponsibility.models;

import macior.strategygame.game.BattlesManagement.configuration.MechConfig;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;

public class ArmyTrainingModel extends AreaUnitChangingModel {

    public Building FACTORY;
    public MechFactoryConfig FACTORY_CONFIG;
    public MechConfig MECH_CONFIG;
}
