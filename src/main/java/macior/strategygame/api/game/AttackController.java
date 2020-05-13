package macior.strategygame.api.game;

import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.game.playersControlls.AttackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/attacks")
@RestController
public class AttackController {

    @Autowired
    private AttackService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse attack(@PathVariable String code, @RequestBody AttackRequest request){
        return service.attack(code, request);
    }
}
