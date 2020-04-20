package macior.strategygame.models.game.playersControls;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.game.BoardManagement.Location;

public class AreaConcernedRequest {

    protected Location location;

    public AreaConcernedRequest(){

    }

    public AreaConcernedRequest(@JsonProperty("location") Location location){
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
