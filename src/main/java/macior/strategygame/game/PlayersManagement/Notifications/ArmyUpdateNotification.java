package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.BoardManagement.Location;

//about updating an army
public class ArmyUpdateNotification extends NotificationBase {

    private Location location;
    private Army army;

    public ArmyUpdateNotification(Location location, Army army){
        this.location = location;
        this.army = army;
    }

    @Override
    public String initializeLabel() {
        return "ARMY_UPDATE";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
