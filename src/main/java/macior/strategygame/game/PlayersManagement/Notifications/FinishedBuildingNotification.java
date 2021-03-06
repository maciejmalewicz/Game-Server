package macior.strategygame.game.PlayersManagement.Notifications;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.messages.BuildingMessage;

public class FinishedBuildingNotification extends NotificationBase {

    private Location location;
    private int place;
    private BuildingMessage building;

    public FinishedBuildingNotification(){
        super();
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

    public BuildingMessage getBuilding() {
        return building;
    }

    public void setBuilding(BuildingMessage building) {
        this.building = building;
    }

    @Override
    public String initializeLabel() {
        return "FINISHED_BUILDING";
    }
}
