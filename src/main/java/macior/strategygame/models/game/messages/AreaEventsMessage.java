package macior.strategygame.models.game.messages;

import macior.strategygame.models.game.messages.event_messages.BuildingConcernedEventMessage;
import macior.strategygame.models.game.messages.event_messages.PostponedEventMessage;

import java.util.ArrayList;
import java.util.List;

public class AreaEventsMessage {

    private List<PostponedEventMessage> events = new ArrayList<>();

    public List<PostponedEventMessage> getEvents() {
        return events;
    }

    public void setEvents(List<PostponedEventMessage> events) {
        this.events = events;
    }

    public void add(PostponedEventMessage msg){
        events.add(msg);
    }
}
