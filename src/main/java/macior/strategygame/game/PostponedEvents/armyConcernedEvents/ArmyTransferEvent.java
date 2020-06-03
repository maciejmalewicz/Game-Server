package macior.strategygame.game.PostponedEvents.armyConcernedEvents;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.PlayersManagement.Notifications.ArmyUpdateNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.models.game.messages.event_messages.ArmyTransferEventMessage;

public class ArmyTransferEvent extends PostponedEvent {

    private AreaUnit sendingAreaUnit;
    private AreaUnit targetArea;
    private Player sender;
    private Army army;

    public ArmyTransferEvent(int finishingTime, AreaUnit sendingAreaUnit, AreaUnit targetArea, Player sender, Army army){
        super(finishingTime);
        this.sendingAreaUnit = sendingAreaUnit;
        this.targetArea = targetArea;
        this.sender = sender;
        this.army = army;
    }

    @Override
    protected void doNotification() {
        Army army = targetArea.getArmy();
        ArmyUpdateNotification notification = new ArmyUpdateNotification(targetArea.getLocation(), army);
        targetArea.getOwner().getInbox().addNotification(notification);
    }

    @Override
    protected void doHappen() {
        Army toUpdate = targetArea.getArmy();
        toUpdate.addDroids(army.getDroids());
        toUpdate.addTanks(army.getTanks());
        toUpdate.addCannons(army.getCannons());
        sendingAreaUnit.getEventsQueue().removeEvent(this);
        targetArea.getEventsQueue().removeEvent(this);
    }

    public ArmyTransferEventMessage toMessage(){
        ArmyTransferEventMessage message = new ArmyTransferEventMessage();
        message.setArmy(army);
        message.setFrom(sendingAreaUnit.getLocation());
        message.setTo(targetArea.getLocation());
        message.setFinishingTime(getFinishingTime());
        return message;
    }

    public AreaUnit getSendingAreaUnit() {
        return sendingAreaUnit;
    }

    public void setSendingAreaUnit(AreaUnit sendingAreaUnit) {
        this.sendingAreaUnit = sendingAreaUnit;
    }

    public AreaUnit getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(AreaUnit targetArea) {
        this.targetArea = targetArea;
    }

    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
