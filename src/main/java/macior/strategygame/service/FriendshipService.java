package macior.strategygame.service;

import macior.strategygame.dao.HistoryDAO;
import macior.strategygame.dao.friendship.Friend;
import macior.strategygame.dao.friendship.FriendsResponse;
import macior.strategygame.dao.friendship.FriendshipDAO;
import macior.strategygame.dao.friendship.InvitationDAO;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.models.User;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.friends.Invitation;
import macior.strategygame.models.friends.InvitationsResponse;
import macior.strategygame.models.friends.ProfileResponse;
import macior.strategygame.models.game_history_management.HistoryUnit;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendshipService {

    @Autowired
    @Qualifier("friendshipDAO")
    private FriendshipDAO friendshipDAO;

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    @Qualifier("userDAO")
    private UserDAO userDAO;

    @Autowired
    @Qualifier("historyDAO")
    private HistoryDAO historyDAO;

    @Autowired
    @Qualifier("invitationDAO")
    private InvitationDAO invitationDAO;

    //here i also notify friends that i logged in!
    public FriendsResponse getFriends(String code){
        int id = mapper.getId(code);
        FriendsResponse response = new FriendsResponse();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        User user = userDAO.getById(id).orElse(null);
        if (user == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        List<Friend> friends = friendshipDAO.joinFriends(user);
        response.setFriends(friends);
        return response;
    }

    public ProfileResponse getByLogin(String code, String login){
        ProfileResponse response = new ProfileResponse();
        //authentication
        int id = mapper.getId(code);
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }
        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        User user = userDAO.getByLogin(login);
        if (user == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        System.out.println(user);
        List<HistoryUnit> list = historyDAO.getGamesHistory(user);
        response.setHistory(list);
        response.setExperience(user.getExperience());
        response.setLogin(login);
        return response;
    }

    public StatusResponse invitePlayer(String code, String login){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        User sender = userDAO.getById(id).orElse(null);
        if (sender == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        User receiver = userDAO.getByLogin(login);

        if (receiver == null){
            response.setStatus(-2);
            return response;
        }

        //... check if guy isn't inviting himself
        if (sender.getId() == receiver.getId()){
            response.setStatus(-3);
            return response;
        }

        //check if invitation hasn't been sent already
        Invitation alreadySent = invitationDAO.findInvitation(sender, receiver);
        if (alreadySent != null){
            response.setStatus(-4);
            return response;
        }

        //after validating sender and receiver, we must check whether receiver isn't already users friend
        boolean areFriends = friendshipDAO.areFriends(sender, receiver);
        if (areFriends){
            response.setStatus(-5);
            return response;
        }

        //again, we have to check whether the receiver hasn't invited sender before
        Invitation reversed = invitationDAO.findInvitation(receiver, sender);
        if (reversed != null){
            response.setStatus(-6);
            return response;
        }

        //now we can send invitation
        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setReceiver(receiver);
        int result = invitationDAO.sendInvitation(invitation);
        if (result != 0){
            response.setStatus(-7);
        }
        //at the end, notify the player
        mapper.addInvitationToInbox(receiver.getId(), sender.getLogin());
        response.setStatus(0);
        return response;
    }

    public InvitationsResponse getInvitations(String code){
        int id = mapper.getId(code);
        InvitationsResponse response = new InvitationsResponse();

        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        User user = userDAO.getById(id).orElse(null);
        if (user == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        List<Invitation> invitationsReceived = invitationDAO.getInvitations(user);
        List<Invitation> invitationsSent = invitationDAO.getSentInvitations(user);
        //map to logins
        List<String> strings = invitationsReceived.stream()
                .map(invitation -> invitation.getSender().getLogin())
                .collect(Collectors.toList());
        response.setInvitationsReceived(strings);

        List<String> sentStrings = invitationsSent.stream()
                .map(invitation -> invitation.getReceiver().getLogin())
                .collect(Collectors.toList());
        response.setInvitationsSent(sentStrings);

        return response;
    }

    public StatusResponse deleteInvitation(String code, String senderLogin){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        User receiver = userDAO.getById(id).orElse(null);
        if (receiver == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        //trying to delete invitation from non-existing user
        //but user will just know that there is no such invitation
        User sender = userDAO.getByLogin(senderLogin);
        if (sender == null){
            response.setStatus(-2);
            return response;
        }

        Invitation invitation = invitationDAO.findInvitation(sender, receiver);
        //trying to delete non-existing invitation
        if (invitation == null){
            response.setStatus(-2);
            return response;
        }

        int result = invitationDAO.deleteInvitation(invitation);
        if (result != 0){
            response.setStatus(-3);
            return response;
        }

        //and notify as always
        mapper.addInvitationRejection(sender.getId(), receiver.getLogin());
        response.setStatus(0);
        return response;
    }

    //copied above, with additionally adding new friendship
    public StatusResponse acceptInvitation(String code, String senderLogin){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        response.setStatus(-1);
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        User receiver = userDAO.getById(id).orElse(null);
        if (receiver == null){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        //trying to delete invitation from non-existing user
        //but user will just know that there is no such invitation
        User sender = userDAO.getByLogin(senderLogin);
        if (sender == null){
            response.setStatus(-2);
            return response;
        }

        Invitation invitation = invitationDAO.findInvitation(sender, receiver);
        //trying to delete non-existing invitation
        if (invitation == null){
            response.setStatus(-2);
            return response;
        }

        int result = invitationDAO.deleteInvitation(invitation);
        if (result != 0){
            response.setStatus(-3);
            return response;
        }

        int addingResult = friendshipDAO.addFriends(sender, receiver);
        if (addingResult != 0){
            response.setStatus(-4);
            return response;
        }
        //at the end, notify
        mapper.addInvitationAcceptance(sender.getId(), receiver.getLogin());
        response.setStatus(0);
        return response;
    }
}
