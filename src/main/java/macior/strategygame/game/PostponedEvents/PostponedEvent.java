package macior.strategygame.game.PostponedEvents;

public abstract class PostponedEvent implements Comparable<PostponedEvent>{

    public abstract void happen();

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
