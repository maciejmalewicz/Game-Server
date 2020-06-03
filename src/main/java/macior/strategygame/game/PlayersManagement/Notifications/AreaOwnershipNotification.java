package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BoardManagement.Location;

public class AreaOwnershipNotification extends NotificationBase {

    private Location location;
    private String owner;

    @Override
    public String initializeLabel() {
        return "AREA_OWNERSHIP";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
