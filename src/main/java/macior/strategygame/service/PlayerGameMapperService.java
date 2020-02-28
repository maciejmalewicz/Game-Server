package macior.strategygame.service;

import macior.strategygame.dao.users.UserDAO;
import macior.strategygame.game.Game;
import macior.strategygame.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public final class PlayerGameMapperService {

    private HashMap<String, Integer> codeToId = new HashMap<>();
    private HashMap<Integer, String> idToCode = new HashMap<>();
    private HashMap<Integer, Game> idToGame = new HashMap<>();

    @Autowired
    private UserDAO userDAO;

    public PlayerGameMapperService(){
        System.out.println("MAPPER");
        //todo delete this - just for tests
        codeToId.put("an", 5);
        idToCode.put(5, "an");
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
        codeToId.remove(idToCode.get(id));
        idToCode.remove(id);

        codeToId.put(code, id);
        idToCode.put(id, code);
        return code;
    }

    public int getId(String code){
        return codeToId.getOrDefault(code, -1);
       // return Optional.of(codeToId.get(code)).orElse(-1);
    }

    //each time we update the code when we receive a message
    public String updateCode(String code){

        //if there is a guy like that, then change maps
        if (codeToId.containsKey(code)){
            int playersId = codeToId.remove(code);
            idToCode.remove(playersId);
            //then generate new code for him and store
            String newCode = UUID.randomUUID().toString();
            codeToId.put(newCode, playersId);
            idToCode.put(playersId, newCode);
            return newCode;
        }
        return "UNKNOWN CODE!";
    }
}
