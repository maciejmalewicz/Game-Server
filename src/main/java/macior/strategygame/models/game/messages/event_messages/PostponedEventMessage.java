package macior.strategygame.models.game.messages.event_messages;

public class PostponedEventMessage implements Comparable<BuildingConcernedEventMessage> {

    protected int finishingTime;

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }

    @Override
    public int compareTo(BuildingConcernedEventMessage other) {
        return finishingTime - other.finishingTime;
    }
}
