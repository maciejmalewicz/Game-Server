package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.Game;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class EventHandler {

    private Game game;

    private TreeSet<PostponedEvent> upcomingEvents = new TreeSet<>();

    public EventHandler(Game game){
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public synchronized void addEvent(PostponedEvent event){
        this.upcomingEvents.add(event);
    }

    //every second we trigger all events that are already to pass
    public void triggerEvents(){
        short time = game.getTimeManager().getSecondsFromStart();
        while (true){
            synchronized (this){
                if (upcomingEvents.isEmpty()){
                    break;
                }
                PostponedEvent event = upcomingEvents.first();
                //do until there is no event or oldest event is still not yet
                if (event.getFinishingTime() > time){
                    break;
                }
                upcomingEvents.pollFirst().happen();
            }

        }
    }

    public TreeSet<PostponedEvent> getUpcomingEvents() {
        return upcomingEvents;
    }
}
