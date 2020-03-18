package macior.strategygame.api.game;

import com.fasterxml.jackson.annotation.JsonProperty;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.game.playersControlls.BuildNewBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/build")
@RestController
public class BuildNewBuildingController {

    @Autowired
    private BuildNewBuildingService service;

    @PostMapping(path = "{code}")
    @CrossOrigin
    public TimeResponse buildNew(@PathVariable("code") String code, @RequestBody BuildingRequest request){
        System.out.println(request);
        return service.buildNew(request, code);
    }
}
