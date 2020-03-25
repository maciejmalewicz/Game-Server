package macior.strategygame.game.BoardManagement;

import macior.strategygame.game.PostponedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.PostponedEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BuildingQueue {

    private List<BuildingConstructionEvent> events = new ArrayList<>();

    public void pushEvent(BuildingConstructionEvent event){
        for (int i = events.size()-1; i >= 0; i--){
            if (events.get(i).getFinishingTime() < event.getFinishingTime()){
                events.add(i+1, event);
                return;
            }
        }
        events.add(0, event);
    }

    public void removeEvent(BuildingConstructionEvent event){
        events.remove(event);
    }

    public List<BuildingConstructionEvent> getEvents() {
        return events;
    }

    public void setEvents(List<BuildingConstructionEvent> events) {
        this.events = events;
    }
}
