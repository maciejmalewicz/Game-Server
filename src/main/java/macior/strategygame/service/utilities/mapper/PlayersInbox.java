package macior.strategygame.service.utilities.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayersInbox {

    private List<String> playersInvitations = new ArrayList<>(); //handled in friendshipService when inviting
    private List<String> invitationsAcceptance = new ArrayList<>();  //both also in friendshipService in accept/delete
    private List<String> invitationsRejection = new ArrayList<>();
    private List<String> logins = new ArrayList<>(); //handled in friendshipDAO when loading friends
    //and their activity (kinda bad but works)


    public PlayersInbox clone(){
        PlayersInbox inbox = new PlayersInbox();

        List<String> clonedInvitations = new ArrayList<>(playersInvitations);
        inbox.setPlayersInvitations(clonedInvitations);

        List<String> clonedAcceptances = new ArrayList<>(invitationsAcceptance);
        inbox.setInvitationsAcceptance(clonedAcceptances);

        List<String> clonedRejections = new ArrayList<>(invitationsRejection);
        inbox.setInvitationsRejection(clonedRejections);

        List<String> clonedLogins = new ArrayList<>(logins);
        inbox.setLogins(clonedLogins);

        return inbox;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    public void addLoggingIn(String login){
        logins.add(login);
    }

    public void addRejection(String rejection){
        invitationsRejection.add(rejection);
    }

    public List<String> getInvitationsRejection() {
        return invitationsRejection;
    }

    public void setInvitationsRejection(List<String> invitationsRejection) {
        this.invitationsRejection = invitationsRejection;
    }

    public List<String> getInvitationsAcceptance() {
        return invitationsAcceptance;
    }

    public void setInvitationsAcceptance(List<String> invitationsAcceptance) {
        this.invitationsAcceptance = invitationsAcceptance;
    }

    public void addAcceptance(String from){
        invitationsAcceptance.add(from);
    }

    public List<String> getPlayersInvitations() {
        return playersInvitations;
    }

    public void setPlayersInvitations(List<String> playersInvitations) {
        this.playersInvitations = playersInvitations;
    }

    public void clear(){
        playersInvitations.clear();
        invitationsRejection.clear();
        invitationsAcceptance.clear();
        logins.clear();
    }

    public void addInvitation(String from){
        playersInvitations.add(from);
    }

    public void deleteInvitation(String from){
        playersInvitations = playersInvitations.stream().filter(a -> !a.equals(from)).collect(Collectors.toList());
    }
}
