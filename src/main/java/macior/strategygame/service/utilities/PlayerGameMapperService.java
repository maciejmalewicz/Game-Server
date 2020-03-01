package macior.strategygame.service.utilities;

import javassist.compiler.ast.Pair;
import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.game.Game;
import macior.strategygame.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public final class PlayerGameMapperService {

    private HashMap<String, Integer> codeToId = new HashMap<>();
    private HashMap<Integer, String> idToCode = new HashMap<>();
    //private HashMap<Integer, Game> idToGame = new HashMap<>(); //old version
    private HashMap<String, IDData> codeToIdDate = new HashMap<>();

    //after how many minutes of not being active player is being kicked out
    private int kickTimerMinutes = 2;
    private long kickTimer = kickTimerMinutes*60000; //into miliseconds

    @Autowired
    private UserDAO userDAO;

    public PlayerGameMapperService(){
        System.out.println("MAPPER");
        //todo delete this - just for tests
        //codeToId.put("an", 5);
        codeToIdDate.put("an", new IDData(5));
        idToCode.put(5, "an");
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
            System.out.println(codeToId);
            System.out.println(idToCode);
            return code;
        }
    }

    //when we log in, we add player to memory and return access code
    public String addLoggedPlayer(int id){
        String code = UUID.randomUUID().toString();
        //codeToId.remove(idToCode.get(id));
        codeToIdDate.remove(idToCode.get(id));
        idToCode.remove(id);

        //codeToId.put(code, id);
        codeToIdDate.put(code, new IDData(id));
        idToCode.put(id, code);
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
        });
        System.out.println(idToCode);
        System.out.println(codeToIdDate);
    }
}
