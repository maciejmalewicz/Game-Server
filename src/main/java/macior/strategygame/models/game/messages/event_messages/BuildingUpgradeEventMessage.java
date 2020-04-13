package macior.strategygame.models.game.messages.event_messages;

public class BuildingUpgradeEventMessage extends BuildingConcernedEventMessage {

    private int LEVEL;

    public int getLEVEL() {
        return LEVEL;
    }

    public void setLEVEL(int LEVEL) {
        this.LEVEL = LEVEL;
    }
}
