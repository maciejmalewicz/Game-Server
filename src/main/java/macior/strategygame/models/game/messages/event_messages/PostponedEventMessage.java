package macior.strategygame.models.game.messages.event_messages;

public abstract class PostponedEventMessage implements Comparable<PostponedEventMessage> {

    protected int finishingTime;
    protected String label;

    public PostponedEventMessage(){
        label = initializeLabel();
    }

    public int getFinishingTime() {
        return finishingTime;
    }

    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public int compareTo(PostponedEventMessage other) {
        return finishingTime - other.finishingTime;
    }

    protected abstract String initializeLabel();
}
