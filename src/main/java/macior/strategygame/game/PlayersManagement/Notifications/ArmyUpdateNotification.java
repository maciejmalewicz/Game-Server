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
}
