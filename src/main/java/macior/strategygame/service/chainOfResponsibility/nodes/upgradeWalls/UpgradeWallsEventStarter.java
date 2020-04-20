package macior.strategygame.service.chainOfResponsibility.nodes.upgradeWalls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeWallsModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsEventStarter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        Building building = wallsModel.BUILDING_UPGRADED;
        BuildingConcernedEvent event;
        EventFactory eventFactory = wallsModel.PLAYER.getGame().getEventFactory();
        if (building == null){
            Walls walls = new Walls();
            UnderConstructionBuilding ucb = setUnderConstructionBuilding(wallsModel, walls);
            event = eventFactory.generateBuildingConstructionEvent(wallsModel.FINISHING_TIME, ucb);
        } else {
            event = eventFactory.generateBuildingUpgradeEvent(wallsModel.FINISHING_TIME, wallsModel.BUILDING_UPGRADED,
                    wallsModel.NEXT_LEVEL, wallsModel.AREA_UNIT, 6);
        }
        wallsModel.PLAYER.getGame().getEventHandler().addEvent(event);
        wallsModel.AREA_UNIT.getBuildingQueue().pushEvent(event);
    }

    private UnderConstructionBuilding setUnderConstructionBuilding(UpgradeWallsModel wallsModel, Walls walls){
        Location location = wallsModel.REQUEST.getLocation();
        AreaUnit unit = wallsModel.PLAYER.getGame().getBoard().getAreaUnit(location);

        UnderConstructionBuilding ucb = new UnderConstructionBuilding(walls, unit, 6);
        unit.setWalls(ucb);
        return ucb;
    }
}
