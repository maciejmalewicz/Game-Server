package macior.strategygame.game.PostponedEvents.armyConcernedEvents;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Notifications.ArmyUpdateNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationsInbox;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.models.game.messages.event_messages.ArmyTrainingEventMessage;

public class ArmyTrainingEvent extends PostponedEvent {

    private AreaUnit area;
    private int unitType;
    private int quantity;

    public ArmyTrainingEvent(int time, AreaUnit areaUnit, int unitType, int quantity){
        super(time);
        this.area = areaUnit;
        this.unitType = unitType;
        this.quantity = quantity;
    }

    public ArmyTrainingEventMessage toMessage(){
        ArmyTrainingEventMessage message = new ArmyTrainingEventMessage();
        message.setFinishingTime(getFinishingTime());
        message.setQuantity(quantity);
        message.setUnitType(unitType);
        return message;
    }

    @Override
    protected NotificationBase doNotification() {
        NotificationsInbox inbox = area.getOwner().getInbox();
        ArmyUpdateNotification notification = new ArmyUpdateNotification(area.getLocation(), area.getArmy());
        inbox.addNotification(notification);
        return notification;
    }

    @Override
    protected void doHappen() {
        area.getEventsQueue().removeEvent(this);
        Army army = area.getArmy();
        switch (unitType){
            case 1:
                army.addDroids(quantity);
                break;
            case 2:
                army.addTanks(quantity);
                break;
            case 3:
                army.addCannons(quantity);
                break;
        }
    }

    public AreaUnit getArea() {
        return area;
    }

    public void setArea(AreaUnit area) {
        this.area = area;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
