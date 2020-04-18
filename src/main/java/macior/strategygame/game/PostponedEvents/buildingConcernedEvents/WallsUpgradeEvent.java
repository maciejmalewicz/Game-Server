package macior.strategygame.game.PostponedEvents.buildingConcernedEvents;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Notifications.FinishedWallsNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;
import macior.strategygame.models.game.messages.event_messages.FinishedWallsMessage;

public class WallsUpgradeEvent extends BuildingConcernedEvent {

    private AreaUnit areaUnit;
    private int level;

    public WallsUpgradeEvent(int time, Building building, AreaUnit area, int level){
        super(time, building);
        areaUnit = area;
        this.level = level;
    }

    @Override
    public BuildingConcernedEventMessage toMessage(int place) {
        FinishedWallsMessage message = new FinishedWallsMessage();
        message.setFinishingTime(this.getFinishingTime());
        message.setLevel(level);
        message.setLocation(areaUnit.getLocation());
        return message;
    }

    @Override
    protected NotificationBase doNotification() {
        FinishedWallsNotification notification = new FinishedWallsNotification(level, areaUnit.getLocation());
        areaUnit.getOwner().getInbox().addNotification(notification);
        return notification;
    }

    @Override
    protected void doHappen() {
        areaUnit.getBuildingQueue().removeEvent(this);
        if (level == 1){
            buildWalls();
        } else {
            upgradeWalls();
        }
    }

    private void buildWalls(){
        UnderConstructionBuilding b = (UnderConstructionBuilding)building;
        b.unpackBuilding();
    }


    private void upgradeWalls(){
        Building building = this.getBuilding();
        if (building.getClass() == UnderConstructionBuilding.class){
            UnderConstructionBuilding underConstruction = (UnderConstructionBuilding)building;
            building = underConstruction.getBuildingUnderConstruction();
        }
        building.setLevel(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public AreaUnit getAreaUnit() {
        return areaUnit;
    }

    public void setAreaUnit(AreaUnit areaUnit) {
        this.areaUnit = areaUnit;
    }
}
