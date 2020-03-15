package macior.strategygame.api.game;

import macior.strategygame.models.game.configuration.GameConfigurationResponse;
import macior.strategygame.service.game.GameConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/config")
@RestController
public class GameConfigurationController {

    @Autowired
    private GameConfigurationService configurationService;

    @GetMapping(path = "{code}")
    public GameConfigurationResponse getGameConfiguration(@PathVariable("code") String code){
        return configurationService.getGameConfiguration(code);
    }
}
