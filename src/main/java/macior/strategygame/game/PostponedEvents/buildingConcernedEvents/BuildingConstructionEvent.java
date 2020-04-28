package macior.strategygame.game.PostponedEvents.buildingConcernedEvents;

import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Notifications.FinishedBuildingNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.models.game.messages.BuildingMessage;
import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;

public class BuildingConstructionEvent extends BuildingConcernedEvent {

    public BuildingConstructionEvent(int time, UnderConstructionBuilding building){
        super(time, building);
    }

    @Override
    public void doHappen(){
        UnderConstructionBuilding b = (UnderConstructionBuilding)building;
        b.getAreaUnit().getEventsQueue().removeEvent(this);
        b.unpackBuilding();
    }

    @Override
    protected NotificationBase doNotification() {
        FinishedBuildingNotification notification = new FinishedBuildingNotification();
        UnderConstructionBuilding b = (UnderConstructionBuilding)building;
        notification.setBuilding(b.getBuildingUnderConstruction().toMessage());
        notification.setLocation(b.getAreaUnit().getLocation());
        notification.setPlace(b.getPlace());

        //get inbox of owner of area unit, where the building has been built
        b.getAreaUnit().getOwner().getInbox().addNotification(notification);
        return notification;
    }

    @Override
    public BuildingConcernedEventMessage toMessage(int place){
        BuildingConcernedEventMessage message = new BuildingConcernedEventMessage();
        message.setFinishingTime(getFinishingTime());
        message.setPlace(place);
        BuildingMessage buildingMessage = getBuilding().toMessage();
        message.setBuilding(buildingMessage);
        return message;
    }

}
