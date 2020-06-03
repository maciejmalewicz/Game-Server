package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;
import macior.strategygame.models.game.messages.event_messages.PostponedEventMessage;

public abstract class PostponedEvent implements Comparable<PostponedEvent>{

    protected abstract void doNotification();
    protected abstract void doHappen();
    protected byte id;



    public void happen(){
        this.doHappen();
        this.doNotification();
    }

    private int finishingTime;

    public PostponedEvent(int time){
        this.finishingTime = time;
    }

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    @Override
    public int compareTo(PostponedEvent other) {
        int result = finishingTime - other.finishingTime;
        if (result != 0){
            return result;
        }
        return id - other.id;
    }
}
