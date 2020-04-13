package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.models.game.messages.event_messages.PostponedEventMessage;

//test object
public class PostponedPrinter extends PostponedEvent {

    public PostponedPrinter(int time){
        super(time);
    }

    @Override
    public void doHappen() {
        System.out.println("Dupa " + this.getFinishingTime());
    }

    @Override
    public NotificationBase doNotification() {
        return null;
    }

    @Override
    public String toString(){
        return this.getFinishingTime() + "";
    }
}
