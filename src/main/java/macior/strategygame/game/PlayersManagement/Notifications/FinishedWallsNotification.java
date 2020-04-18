package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BoardManagement.Location;

public class FinishedWallsNotification extends NotificationBase {

    private int level;
    private Location location;

    public FinishedWallsNotification(int level, Location location){
        this.level = level;
        this.location = location;
    }

    @Override
    public String initializeLabel() {
        return "FINISHED_WALLS";
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
