package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.PostponedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.PostponedEvent;

import java.util.*;

public class BuildingQueue {

    //private List<BuildingConcernedEvent> events = new ArrayList<>();
    private TreeSet<BuildingConcernedEvent> events = new TreeSet<>();

    public synchronized void pushEvent(BuildingConcernedEvent event){
        System.out.println(events.add(event));
    }

    public synchronized void removeEvent(BuildingConcernedEvent event){
        events.remove(event);
    }

    public TreeSet<BuildingConcernedEvent> getEvents() {
        return events;
    }

    public void setEvents(TreeSet<BuildingConcernedEvent> events) {
        this.events = events;
    }

    public synchronized BuildingConcernedEvent getLastEventConcerningBuilding(Building building){
        Iterator<BuildingConcernedEvent> iterator = events.descendingIterator();
        while (iterator.hasNext()){
            BuildingConcernedEvent event = iterator.next();
            if (event.getBuilding() == building){
                return event;
            }
        }
        return null;
    }
}
