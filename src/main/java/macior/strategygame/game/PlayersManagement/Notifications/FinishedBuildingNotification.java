package macior.strategygame.game.PlayersManagement.Notifications;

import com.sun.xml.bind.v2.schemagen.xmlschema.LocalAttribute;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Location;

public class FinishedBuildingNotification extends NotificationBase {

    private Location location;
    private int place;
    private Building building;

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

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    @Override
    public String initializeLabel() {
        return "FINISHED_BUILDING";
    }
}
