package macior.strategygame.service.playerCommandPipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeWallsModel;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsEventStarter extends ChainNode<UpgradeWallsModel> {

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        Building building = model.BUILDING_UPGRADED;
        BuildingConcernedEvent event;
        EventFactory eventFactory = model.PLAYER.getGame().getEventFactory();
        if (building == null){
            Walls walls = new Walls();
            UnderConstructionBuilding ucb = setUnderConstructionBuilding(model, walls);
            event = eventFactory.generateBuildingConstructionEvent(model.FINISHING_TIME, ucb);
        } else {
            event = eventFactory.generateBuildingUpgradeEvent(model.FINISHING_TIME, model.BUILDING_UPGRADED,
                    model.NEXT_LEVEL, model.AREA_UNIT, 6);
        }
        model.PLAYER.getGame().getEventHandler().addEvent(event);
        model.AREA_UNIT.getEventsQueue().pushEvent(event);
    }

    private UnderConstructionBuilding setUnderConstructionBuilding(UpgradeWallsModel wallsModel, Walls walls){
        Location location = wallsModel.REQUEST.getLocation();
        AreaUnit unit = wallsModel.PLAYER.getGame().getBoard().getAreaUnit(location);

        UnderConstructionBuilding ucb = new UnderConstructionBuilding(walls, unit, 6);
        unit.setWalls(ucb);
        return ucb;
    }
}
