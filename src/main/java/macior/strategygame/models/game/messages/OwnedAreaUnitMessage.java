package macior.strategygame.models.game.messages;

import macior.strategygame.game.BoardManagement.BuildingQueue;

public class OwnedAreaUnitMessage extends AreaUnitMessage {

    public BuildingMessage NORTH_BUILDING;
    public BuildingMessage SOUTH_BUILDING;
    public BuildingMessage WEST_BUILDING;
    public BuildingMessage EAST_BUILDING;
    public WallsMessage WALLS;
    public BuildingQueueMessage BUILDING_QUEUE;

}
