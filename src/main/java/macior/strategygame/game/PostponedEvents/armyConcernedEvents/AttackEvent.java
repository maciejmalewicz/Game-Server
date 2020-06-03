package macior.strategygame.game.PostponedEvents.armyConcernedEvents;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.PlayersManagement.Notifications.AreaDetailsNotification;
import macior.strategygame.game.PlayersManagement.Notifications.AreaOwnershipNotification;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationsInbox;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.models.game.messages.OwnedAreaUnitMessage;
import macior.strategygame.service.executionChain.models.BattleResult;
import macior.strategygame.service.game.battles.BattleService;

public class AttackEvent extends PostponedEvent {

    private Attack attack;
    private BattleService service;
    private BattleResult result;

    public AttackEvent(int time, Attack attack, BattleService battleStartingService){
        super(time);
        this.attack = attack;
        this.service = battleStartingService;
    }

    @Override
    protected void doNotification() {
        if (result.HAS_ATTACKER_WON){
            notifyAboutAreaDetails();
            notifyAboutOwnership();
        }
    }

    private void notifyAboutOwnership(){
        AreaOwnershipNotification ownershipNotification = new AreaOwnershipNotification();
        ownershipNotification.setLocation(attack.TO.getLocation());
        ownershipNotification.setOwner(attack.ATTACKER.getNick());
        attack.ATTACKER.getGame().getPlayersSet().notifyAllPlayers(ownershipNotification);
    }

    private void notifyAboutAreaDetails(){
        AreaDetailsNotification notification = new AreaDetailsNotification();
        notification.setLocation(attack.TO.getLocation());
        notification.setMessage(getAreaUnitMessage());
        NotificationsInbox inbox = attack.ATTACKER.getInbox();
        inbox.addNotification(notification);
    }

    private OwnedAreaUnitMessage getAreaUnitMessage(){
        AreaUnit areaUnit = attack.TO;
        return service.getConverter().convertOwnedAreaUnit(areaUnit);
    }

    @Override
    protected void doHappen() {
        result = service.battle(attack);
    }
}
