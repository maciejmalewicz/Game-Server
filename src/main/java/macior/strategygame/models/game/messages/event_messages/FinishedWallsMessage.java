package macior.strategygame.models.game.messages.event_messages;

import macior.strategygame.game.BoardManagement.Location;

public class FinishedWallsMessage extends BuildingConcernedEventMessage {

    private Location location;
    private int level;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
