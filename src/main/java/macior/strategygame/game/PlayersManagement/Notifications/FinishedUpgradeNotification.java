package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BoardManagement.Location;

public class FinishedUpgradeNotification extends NotificationBase{

    private Location location;
    private int place;
    private int level;

    @Override
    public String initializeLabel() {
        return "FINISHED_UPGRADE";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
