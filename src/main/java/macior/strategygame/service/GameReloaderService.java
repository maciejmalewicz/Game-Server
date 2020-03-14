package macior.strategygame.service;

import macior.strategygame.game.BoardManagement.GameConverter;
import macior.strategygame.game.Game;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.game.GameStateResponse;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameReloaderService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private GameConverter converter;


    public StatusResponse checkIfPlaying(String code){
        int id = mapper.getId(code);
        StatusResponse response = new StatusResponse();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        if (mapper.isPLaying(id)){
            response.setStatus(1);
        } else {
            response.setStatus(0);
        }

        return response;
    }

    public GameStateResponse getGameStatus(String code){
        int id = mapper.getId(code);
        GameStateResponse response = new GameStateResponse();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);

        Player player = mapper.getPlayerById(id);
        if (player == null){
            return response;
        }

        Game game = player.getGame();
        response = converter.convert(game, player, response);
        return response;
    }
}
