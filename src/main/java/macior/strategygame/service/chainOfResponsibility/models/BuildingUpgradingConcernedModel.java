package macior.strategygame.service.chainOfResponsibility.models;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;

public class BuildingUpgradingConcernedModel extends AreaUnitChangingModel {

    public Building BUILDING_UPGRADED;
    public BuildingConcernedEvent LATEST_EVENT;
    public int CURRENT_UPGRADING_LEVEL;
    public int NEXT_LEVEL;
}
