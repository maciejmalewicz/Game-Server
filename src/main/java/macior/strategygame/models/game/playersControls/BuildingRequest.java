package macior.strategygame.models.game.playersControls;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.game.BoardManagement.Location;

public class BuildingRequest {

    private Location location;
    private int place;
    private int building;

    public BuildingRequest(@JsonProperty("location") Location location,
                           @JsonProperty("place") int place, @JsonProperty("building") int building)
    {
        this.location = location;
        this.place = place;
        this.building = building;
    }
    public Location getLocation() {
        return location;
    }

    public int getPlace() {
        return place;
    }

    public int getBuilding() {
        return building;
    }
}
