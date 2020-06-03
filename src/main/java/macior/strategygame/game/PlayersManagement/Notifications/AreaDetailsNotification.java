package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.messages.OwnedAreaUnitMessage;

public class AreaDetailsNotification extends NotificationBase{

    private Location location;
    private OwnedAreaUnitMessage message;

    //todo!
    @Override
    public String initializeLabel() {
        return "AREA_DETAILS";
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public OwnedAreaUnitMessage getMessage() {
        return message;
    }

    public void setMessage(OwnedAreaUnitMessage message) {
        this.message = message;
    }
}
