package macior.strategygame.api.game;

import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.game.playersControlls.ArmyTransferringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/armyTransfers")
@RestController
public class TransferingArmyController {

    @Autowired
    private ArmyTransferringService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse transferArmy(@PathVariable("code") String code, @RequestBody ArmyTransferRequest request){
        return service.transferArmy(code, request);
    }
}
