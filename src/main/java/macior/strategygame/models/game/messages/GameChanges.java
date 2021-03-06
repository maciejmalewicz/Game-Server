package macior.strategygame.models.game.messages;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationsInbox;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.StatusResponse;

public class GameChanges extends StatusResponse {


    private byte status = 0;
    private ResourceSet resources;
    private short timeFromStart;
    private NotificationsInbox inbox;

    public NotificationsInbox getInbox() {
        return inbox;
    }

    public void setInbox(NotificationsInbox inbox) {
        this.inbox = inbox;
    }

    public ResourceSet getResources() {
        return resources;
    }

    public void setResources(ResourceSet resources) {
        this.resources = resources;
    }

    public short getTimeFromStart() {
        return timeFromStart;
    }

    public void setTimeFromStart(short timeFromStart) {
        this.timeFromStart = timeFromStart;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
