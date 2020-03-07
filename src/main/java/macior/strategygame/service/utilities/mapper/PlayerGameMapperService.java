package macior.strategygame.service.utilities.mapper;

import javassist.compiler.ast.Pair;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.User;
import macior.strategygame.service.utilities.IDData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public final class PlayerGameMapperService {

    //player and code handling
    private HashMap<Integer, String> idToCode = new HashMap<>();
    private HashMap<String, IDData> codeToIdDate = new HashMap<>();
    //inbox handling (menu notifications, messages)
    private HashMap<Integer, PlayersInbox> idToInbox = new HashMap<>();
    //user -> player mapping, where player is one who plays some game
    private HashMap<Integer, Player> idToPlayer = new HashMap<>();
    private HashMap<Player, Integer> playerToId = new HashMap<>();

    //after how many minutes of not being active player is being kicked out
    private int kickTimerMinutes = 2;
    private long kickTimer = kickTimerMinutes*60000; //into miliseconds

    @Autowired
    private UserDAO userDAO;

    public PlayerGameMapperService(){
        System.out.println("MAPPER");
        //todo delete this - just for tests
        //codeToId.put("an", 5);
        //codeToIdDate.put("an", new IDData(5));
        //idToCode.put(5, "an");
    }

    public boolean isActive(int id){
        return idToCode.containsKey(id);
    }

    //logs player in and returns his access code
    public String logIn(User user){
        User result = userDAO.getAccount(user.getLogin(), user.getPassword()).orElse(null);
        if (result == null){
            return "NOT FOUND";
        } else {
            int id = result.getId();
            String code = addLoggedPlayer(id);
//            System.out.println(codeToId);
//            System.out.println(idToCode);
            return code;
        }
    }

    //when we log in, we add player to memory and return access code
    public String addLoggedPlayer(int id){
        String code = UUID.randomUUID().toString();
        codeToIdDate.remove(idToCode.get(id));
        idToCode.remove(id);

        //clearing inbox
        idToInbox.remove(id);

        codeToIdDate.put(code, new IDData(id));
        idToCode.put(id, code);
        idToInbox.put(id, new PlayersInbox());
        return code;
    }

    //-1 if no id is found
    public int getId(String code){
        //return codeToId.getOrDefault(code, -1);
        IDData data  = codeToIdDate.getOrDefault(code, null);
        if (data == null){
            return -1;
        }
        return data.getId();
    }

    //each time we update the code when we receive a message
    //update: we also generate date, so old entries can be deleted
    public String updateCode(String code){

        //if there is a guy like that, then change maps
        if (codeToIdDate.containsKey(code)){
            //int playersId = codeToId.remove(code);
            IDData data = codeToIdDate.remove(code);
            int playersId = data.getId();
            idToCode.remove(playersId);
            //then generate new code for him and store
            String newCode = UUID.randomUUID().toString();
            //codeToId.put(newCode, playersId);
            codeToIdDate.put(newCode, new IDData(playersId));
            idToCode.put(playersId, newCode);
            return newCode;
        }
        return "UNKNOWN CODE";
    }

    public PlayersInbox checkInbox(int id){
        PlayersInbox inbox = idToInbox.get(id);
        PlayersInbox cloned;
        if (inbox == null){
            cloned = new PlayersInbox();
        } else {
            cloned = inbox.clone();
            inbox.clear();
        }
        return cloned;
    }

    public void addInvitationToInbox(int id, String from){
        if (isActive(id)){
            //System.out.println("SENDING INVITATION!!!!");
            PlayersInbox inbox = idToInbox.get(id);
            inbox.addInvitation(from);
        }
    }

    public void addInvitationAcceptance(int id, String from){
        if (isActive(id)){
            PlayersInbox inbox = idToInbox.get(id);
            inbox.addAcceptance(from);
        }
    }

    public void addInvitationRejection(int id, String from){
        if (isActive(id)){
            PlayersInbox inbox = idToInbox.get(id);
            inbox.addRejection(from);
        }
    }

    public void notifyAboutLoggingIn(int id, String newcomer){
        if (isActive(id)){
            PlayersInbox inbox = idToInbox.get(id);
            inbox.addLoggingIn(newcomer);
        }

    }

    public void clearOldCodes(){
        System.out.println(codeToIdDate);
        System.out.println(idToCode);
        List<String> entries = codeToIdDate.entrySet().stream()
                .filter(entry -> (new Date().getTime() - entry.getValue().getDate().getTime()) > kickTimer)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        entries.forEach(code -> {
            int id = codeToIdDate.get(code).getId();
            codeToIdDate.remove(code);
            idToCode.remove(id);
            idToInbox.remove(id);
        });
        System.out.println(idToCode);
        System.out.println(codeToIdDate);
    }

    public void addPlayer(int id, Player player){
        this.idToPlayer.remove(id);
        this.idToPlayer.put(id, player);
        this.playerToId.remove(player);
        this.playerToId.put(player, id);
    }

    public void removePlayer(int id){
        Player player = idToPlayer.remove(id);
        if (player != null){
            this.playerToId.remove(player);
        }
    }

    public boolean isPLaying(int id){
        return this.idToPlayer.containsKey(id);
    }
}
