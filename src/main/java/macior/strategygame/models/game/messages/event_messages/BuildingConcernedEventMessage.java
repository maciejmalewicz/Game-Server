package macior.strategygame.models.game.messages.event_messages;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.models.game.messages.BuildingMessage;

public class BuildingConcernedEventMessage extends PostponedEventMessage {

    protected BuildingMessage building;
    protected int place;

    public BuildingMessage getBuilding() {
        return building;
    }

    public void setBuilding(BuildingMessage building) {
        this.building = building;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    @Override
    protected String initializeLabel() {
        return "BUILDING_EVENT";
    }


}
