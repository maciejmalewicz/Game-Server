package macior.strategygame.service.game;

import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.configuration.GameConfigurationResponse;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameConfigurationService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private GameConfiguration configuration;

    public GameConfigurationResponse getGameConfiguration(String code){
        int id = mapper.getId(code);
        GameConfigurationResponse response = new GameConfigurationResponse();
        if (id == -1){
            response.setCode("UNKNOWN CODE");
            return response;
        }

        String newCode = mapper.updateCode(code);
        response.setCode(newCode);
        response.setConfiguration(configuration);
        return response;
    }
}
