package macior.strategygame.models;

import macior.strategygame.service.utilities.mapper.PlayersInbox;

public class NotificationResponse extends StatusResponse {

    private PlayersInbox inbox;

    public PlayersInbox getInbox() {
        return inbox;
    }

    public void setInbox(PlayersInbox inbox) {
        this.inbox = inbox;
    }
}
