package macior.strategygame.game.PostponedEvents.buildingConcernedEvents;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.bigBuildings.BigBuilding;
import macior.strategygame.game.PlayersManagement.Notifications.FinishedUpgradeNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.models.game.messages.BuildingMessage;
import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;
import macior.strategygame.models.game.messages.event_messages.BuildingUpgradeEventMessage;

public class BuildingUpgradeEvent extends BuildingConcernedEvent {

    private int level;
    private AreaUnit areaUnit;
    private int place;

    public BuildingUpgradeEvent(int time, Building buildingUpgraded, int level, AreaUnit areaUnit, int place){
        super(time, buildingUpgraded);
        this.level = level;
        this.areaUnit = areaUnit;
        this.place = place;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    protected void doNotification() {
        FinishedUpgradeNotification notification = new FinishedUpgradeNotification();
        notification.setLocation(areaUnit.getLocation());
        notification.setPlace(place);
        notification.setLevel(level);
        if (building instanceof BigBuilding){
            notifyAllPlayers(notification);
        }
        notifyOwner(notification);
    }

    //building big stuff is not a secret
    protected void notifyAllPlayers(FinishedUpgradeNotification notification){
        areaUnit.getOwner().getGame().getPlayersSet().notifyAllPlayers(notification);
    }

    protected void notifyOwner(FinishedUpgradeNotification notification){
        areaUnit.getOwner().getInbox().addNotification(notification);
    }

    @Override
    protected void doHappen() {
        building.setLevel(level);
        areaUnit.getEventsQueue().removeEvent(this);
    }

    @Override
    public BuildingConcernedEventMessage toMessage(int place) {
        BuildingUpgradeEventMessage message = new BuildingUpgradeEventMessage();
        message.setFinishingTime(getFinishingTime());
        message.setPlace(place);
        message.setLEVEL(getLevel());
        BuildingMessage buildingMessage = getBuilding().toMessage();
        message.setBuilding(buildingMessage);
        return message;
    }
}
