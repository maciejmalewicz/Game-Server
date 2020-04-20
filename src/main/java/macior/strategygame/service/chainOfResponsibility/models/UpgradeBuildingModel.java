package macior.strategygame.service.chainOfResponsibility.models;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.models.game.playersControls.UpgradeRequest;

public class UpgradeBuildingModel extends BuildingUpgradingConcernedModel {

    public BuildingConfig BUILDING_CONFIG;

}
