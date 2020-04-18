package macior.strategygame.game.PostponedEvents.buildingConcernedEvents;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;

public abstract class BuildingConcernedEvent extends PostponedEvent {

    protected Building building;
    public abstract BuildingConcernedEventMessage toMessage(int place);

    public BuildingConcernedEvent(int time, Building building){
        super(time);
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }


    @Override
    public boolean equals(Object other){
        if (other.getClass() != this.getClass()){
            return false;
        }
        BuildingConcernedEvent e = (BuildingConcernedEvent)other;
        if (e.getFinishingTime() != this.getFinishingTime()){
            return false;
        }
        return e.getBuilding() == getBuilding();
    }
}
