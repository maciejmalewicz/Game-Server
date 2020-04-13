package macior.strategygame.models.game.messages;

import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;

import java.util.ArrayList;
import java.util.List;

public class BuildingQueueMessage {

    private List<BuildingConcernedEventMessage> events = new ArrayList<>();

    public List<BuildingConcernedEventMessage> getEvents() {
        return events;
    }

    public void setEvents(List<BuildingConcernedEventMessage> events) {
        this.events = events;
    }

    public void add(BuildingConcernedEventMessage msg){
        events.add(msg);
    }
}
