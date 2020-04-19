package macior.strategygame.api.tests;

import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.PlayerChangesModel;
import macior.strategygame.service.chainOfResponsibility.nodes.CodeChangingNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/test")
@RestController
public class FakeController {

    @Autowired
    CodeChangingNode node;

    @GetMapping(path = "{code}")
    public StatusResponse get(@PathVariable("code") String code){
        BuildNewBuildingModel model = new BuildNewBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = new BuildingRequest(null, 0, 0);
        return node.invoke(model);
    }
}
