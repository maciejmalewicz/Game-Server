package macior.strategygame.api.game;

import macior.strategygame.models.game.configuration.GameConfigurationResponse;
import macior.strategygame.service.game.GameConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/config")
@RestController
public class GameConfigurationController {

    @Autowired
    private GameConfigurationService configurationService;

    @GetMapping(path = "{code}")
    @CrossOrigin
    public GameConfigurationResponse getGameConfiguration(@PathVariable("code") String code){
        return configurationService.getGameConfiguration(code);
    }
}
