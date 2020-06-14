package macior.strategygame.service.pipelines.models;

import macior.strategygame.game.BattlesManagement.configuration.MechConfig;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.mechFactories.MechFactoryConfig;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;

public class ArmyTrainingModel extends AreaUnitChangingModel {

    public Building FACTORY;
    public MechFactoryConfig FACTORY_CONFIG;
    public MechConfig MECH_CONFIG;
    public ArmyTrainingEvent EVENT;
}
