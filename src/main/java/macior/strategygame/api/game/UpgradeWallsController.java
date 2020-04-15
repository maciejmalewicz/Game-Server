package macior.strategygame.api.game;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.TimeResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/wallsUpgrading")
@RestController
public class UpgradeWallsController {

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse upgradeWalls(@PathVariable("code") String code, @RequestBody Location location){
        System.out.println("mienso");
        return null;
    }
}
