package macior.strategygame.game.PostponedEvents.armyConcernedEvents;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.service.game.battles.BattleStartingService;

public class AttackEvent extends PostponedEvent {

    private Attack attack;
    private BattleStartingService service;

    public AttackEvent(int time, Attack attack, BattleStartingService battleStartingService){
        super(time);
        this.attack = attack;
        this.service = battleStartingService;
    }

    @Override
    protected NotificationBase doNotification() {
        return null;
    }

    @Override
    protected void doHappen() {
        service.battle(attack);
    }
}
