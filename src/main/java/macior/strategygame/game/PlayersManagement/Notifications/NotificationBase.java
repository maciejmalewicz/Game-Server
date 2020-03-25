package macior.strategygame.game.PlayersManagement.Notifications;

public abstract class NotificationBase {

    protected String label;

    public abstract String initializeLabel();

    public NotificationBase(){
        label = initializeLabel();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
