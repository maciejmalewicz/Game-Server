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
import macior.strategygame.models.friends.ProfileResponse;
import macior.strategygame.models.game_history_management.HistoryUnit;
import macior.strategygame.service.utilities.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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
        List<Friend> friends = friendshipDAO.getFriends(id);
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

        Invitation invitation = new Invitation();
        invitation.setSender(sender);
        invitation.setReceiver(receiver);
        int result = invitationDAO.sendInvitation(invitation);

        response.setStatus(result);
        return response;
    }
}
