package macior.strategygame.models.friends;

import macior.strategygame.models.StatusResponse;

import java.util.List;

public class InvitationsResponse extends StatusResponse {

    private List<String> invitationsSent;
    private List<String> invitationsReceived;

    public List<String> getInvitationsSent() {
        return invitationsSent;
    }

    public void setInvitationsSent(List<String> invitationsSent) {
        this.invitationsSent = invitationsSent;
    }

    public List<String> getInvitationsReceived() {
        return invitationsReceived;
    }

    public void setInvitationsReceived(List<String> invitationsReceived) {
        this.invitationsReceived = invitationsReceived;
    }
}
