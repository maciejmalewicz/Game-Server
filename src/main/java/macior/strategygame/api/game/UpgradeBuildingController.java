package macior.strategygame.api.game;

import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.game.playersControlls.UpgradeBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/upgradeBuilding")
@RestController
public class UpgradeBuildingController {

    @Autowired
    private UpgradeBuildingService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse upgradeBuilding(@PathVariable("code") String code, @RequestBody UpgradeRequest upgradeRequest){
        return service.upgradeBuilding(code, upgradeRequest);
    }
}
