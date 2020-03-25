package macior.strategygame.models.game;

import macior.strategygame.game.BoardManagement.BuildingQueue;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;

public class OwnedAreaUnitMessage extends AreaUnitMessage {

    public BuildingMessage NORTH_BUILDING;
    public BuildingMessage SOUTH_BUILDING;
    public BuildingMessage WEST_BUILDING;
    public BuildingMessage EAST_BUILDING;
    public WallsMessage WALLS;
    public BuildingQueue BUILDING_QUEUE;

}
