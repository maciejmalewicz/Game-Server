package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;

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
