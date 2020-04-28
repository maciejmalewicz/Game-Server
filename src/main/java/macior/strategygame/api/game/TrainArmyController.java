package macior.strategygame.api.game;

import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.game.playersControlls.TrainArmyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/training")
@RestController
public class TrainArmyController {

    @Autowired
    private TrainArmyService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse trainArmy(@PathVariable("code") String code, @RequestBody ArmyTrainingRequest request){
        return service.trainArmy(code, request);
    }
}
