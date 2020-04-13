package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.AIUpgrader;
import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.game.PlayersManagement.Player;

public class AIUpgradeEvent extends PostponedEvent {

    private Player player;
    private AIUpgrader upgrader;
    private EventHandler eventHandler;

    public AIUpgradeEvent(int time, Player player, AIUpgrader upgrader, EventHandler eventHandler){
        super(time);
        this.player = player;
        this.upgrader = upgrader;
        this.eventHandler = eventHandler;
    }

    @Override
    protected NotificationBase doNotification() {
        return null;
    }

    @Override
    protected void doHappen() {
        upgrader.upgradeAI(player);
        //upgrade again if it's possible
        if (upgrader.canBeStillUpgraded(player)){
            this.setFinishingTime(this.getFinishingTime()+60);
            eventHandler.addEvent(this);
        }
    }
}
