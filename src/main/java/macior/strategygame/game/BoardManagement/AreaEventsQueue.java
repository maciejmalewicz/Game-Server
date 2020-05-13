package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PostponedEvents.PostponedEvent;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;

import java.util.*;

public class AreaEventsQueue {

    //private List<BuildingConcernedEvent> events = new ArrayList<>();
    private TreeSet<PostponedEvent> events = new TreeSet<>();

    public synchronized void pushEvent(PostponedEvent event){
        System.out.println(events.add(event));
    }

    public synchronized void removeEvent(PostponedEvent event){
        events.remove(event);
    }

    public TreeSet<PostponedEvent> getEvents() {
        return events;
    }

    public void setEvents(TreeSet<PostponedEvent> events) {
        this.events = events;
    }

    public synchronized BuildingConcernedEvent getLastEventConcerningBuilding(Building building){
        Iterator<PostponedEvent> iterator = events.descendingIterator();
        while (iterator.hasNext()){
            PostponedEvent event = iterator.next();
            //ignore all other events
            if (event instanceof BuildingConcernedEvent){

                BuildingConcernedEvent buildingEvent = (BuildingConcernedEvent)event;
                Building eventBuilding = buildingEvent.getBuilding();
                if (eventBuilding.getClass() == UnderConstructionBuilding.class){
                    eventBuilding = ((UnderConstructionBuilding)eventBuilding).getBuildingUnderConstruction();
                }

                if (eventBuilding == building){
                    return buildingEvent;
                }
            }
        }
        return null;
    }

    public synchronized ArmyTrainingEvent getLastArmyTrainingEvent(int unitType){
        Iterator<PostponedEvent> iterator = events.descendingIterator();
        while (iterator.hasNext()){
            PostponedEvent event = iterator.next();
            //ignore all other events
            if (event.getClass() == ArmyTrainingEvent.class){
                ArmyTrainingEvent trainingEvent = (ArmyTrainingEvent)event;
                if (trainingEvent.getUnitType() == unitType){
                    return trainingEvent;
                }
            }
        }
        return null;
    }
}
