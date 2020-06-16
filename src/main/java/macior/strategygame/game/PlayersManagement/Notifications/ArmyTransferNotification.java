package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.Location;

public class ArmyTransferNotification extends NotificationBase{
    private Location from;
    private Location to;
    private Army army;

    public Location getFrom() {
        return from;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    @Override
    public String initializeLabel() {
        return "ARMY_TRANSFER";
    }
}
