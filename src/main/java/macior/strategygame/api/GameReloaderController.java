package macior.strategygame.api;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.game.GameStateResponse;
import macior.strategygame.service.GameReloaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/getGame")
@RestController()
public class GameReloaderController {

    @Autowired
    private GameReloaderService service;

    @GetMapping(path = "playing/{code}")
    @CrossOrigin
    public StatusResponse checkIfPlaying(@PathVariable("code") String code){
        return service.checkIfPlaying(code);
    }

    @GetMapping(path = "all/{code}")
    @CrossOrigin
    public GameStateResponse getGameState(@PathVariable("code") String code){
        return service.getGameStatus(code);
    }
}
