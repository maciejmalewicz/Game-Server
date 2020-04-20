package macior.strategygame.models.game.playersControls;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.game.BoardManagement.Location;

public class UpgradeRequest extends AreaConcernedRequest {


    //what? north? south? main?
    private int place;

    public UpgradeRequest(@JsonProperty("location") Location location, @JsonProperty("place") int place){
        this.location = location;
        this.place = place;
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
}
