package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.Game;

import java.util.TreeSet;

public class EventHandler {

    private Game game;
    private TreeSet<PostponedEvent> upcomingEvents = new TreeSet<>();

    public EventHandler(Game game){
        this.game = game;
        //only for testing
        //works
//        upcomingEvents.add(new PostponedPrinter(14));
//        upcomingEvents.add(new PostponedPrinter(20));
    }

    public Game getGame() {
        return game;
    }

    public void addEvent(PostponedEvent event){
        this.upcomingEvents.add(event);
    }

    //every second we trigger all events that are already to pass
    public void triggerEvents(){
        short time = game.getTimeManager().getSecondsFromStart();
        while (true){
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
