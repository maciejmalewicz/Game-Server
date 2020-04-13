package macior.strategygame.api.game;

import macior.strategygame.models.game.messages.GameChanges;
import macior.strategygame.service.game.GameChangesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/gameChanges")
@RestController
public class GameChangesController {

    @Autowired
    private GameChangesService service;

    @GetMapping(path = "{code}")
    @CrossOrigin
    public GameChanges getGameChanges(@PathVariable("code") String code){
        return service.getGameChanges(code);
    }
}
