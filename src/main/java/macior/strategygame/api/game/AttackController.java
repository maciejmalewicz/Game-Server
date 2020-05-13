package macior.strategygame.api.game;

import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/attacks")
@RestController
public class AttackController {

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse attack(@PathVariable String code, @RequestBody ArmyTransferRequest request){
        //todo create request model!! Army transfer request must be extended by commander and so on
        return null;
    }
}
