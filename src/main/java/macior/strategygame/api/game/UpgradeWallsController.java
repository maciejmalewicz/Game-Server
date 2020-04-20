package macior.strategygame.api.game;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.game.playersControlls.UpgradeWallsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/wallsUpgrading")
@RestController
public class UpgradeWallsController {

    @Autowired
    private UpgradeWallsService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse upgradeWalls(@PathVariable("code") String code, @RequestBody AreaConcernedRequest request){
        return service.upgradeWalls(code, request);
    }
}
