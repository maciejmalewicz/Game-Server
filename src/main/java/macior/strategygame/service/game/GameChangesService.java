package macior.strategygame.service.game;

import macior.strategygame.game.PlayersManagement.Notifications.NotificationsInbox;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.messages.GameChanges;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameChangesService {

    @Autowired
    private PlayerGameMapperService mapper;

    public GameChanges getGameChanges(String code){
        int id = mapper.getId(code);
        GameChanges gameChanges = new GameChanges();
        if (id == -1){
            gameChanges.setCode("UNKNOWN CODE");
            return gameChanges;
        }
        String newCode = mapper.updateCode(code);
        gameChanges.setCode(newCode);

        Player player = mapper.getPlayerById(id);
        if (player == null){
            gameChanges.setStatus((byte)-1);
            return gameChanges;
        }

        appendGameChanges(gameChanges, player);
        return gameChanges;
    }

    public void appendGameChanges(GameChanges response, Player player){
        short secondsFromStart = player.getGame().getTimeManager().getSecondsFromStart();
        response.setTimeFromStart(secondsFromStart);

        ResourceSet resources = player.getResources();
        response.setResources(resources);

        NotificationsInbox inbox = player.getInbox().cloneAndClear();
        response.setInbox(inbox);

        //todo all other updates
    }
}
