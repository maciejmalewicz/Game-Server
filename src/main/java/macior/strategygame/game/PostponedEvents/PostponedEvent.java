package macior.strategygame.game.PostponedEvents;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationBase;

public abstract class PostponedEvent implements Comparable<PostponedEvent>{

    protected abstract NotificationBase doNotification();
    protected abstract void doHappen();


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

    @Override
    public int compareTo(PostponedEvent other) {
        return finishingTime - other.finishingTime;
    }
}
