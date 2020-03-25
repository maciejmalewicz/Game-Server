package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Notifications.FinishedBuildingNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;

public class BuildingConstructionEvent extends PostponedEvent {

    private UnderConstructionBuilding building;

    public BuildingConstructionEvent(int time, UnderConstructionBuilding building){
        super(time);
        this.building = building;
    }

    @Override
    public void doHappen(){
        building.getAreaUnit().getBuildingQueue().removeEvent(this);
        building.unpackBuilding();
    }

    @Override
    protected NotificationBase doNotification() {
        FinishedBuildingNotification notification = new FinishedBuildingNotification();
        notification.setBuilding(building.getBuildingUnderConstruction());
        notification.setLocation(building.getAreaUnit().getLocation());
        notification.setPlace(building.getPlace());

        //get inbox of owner of area unit, where the building has been built
        building.getAreaUnit().getOwner().getInbox().addNotification(notification);
        return notification;
    }
}
