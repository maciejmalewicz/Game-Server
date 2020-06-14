package macior.strategygame.api.tests;

import executionChains.Chain;
import macior.strategygame.models.account_management.StatusResponse;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.playerCommandPipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.playerCommandPipelines.nodes.*;
import macior.strategygame.service.playerCommandPipelines.nodes.buildNewBuilding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/test")
@RestController
public class FakeController {

    private Chain<BuildNewBuildingModel> chain;

    @Autowired
    public FakeController(CodeChangingNode codeChangingNode,
                          PlayerRetrievingNode playerRetrievingNode,
                          BuildNewBuildingRequestValidator buildNewBuildingRequestValidator,
                          NewBuildingsUpgradesValidator newBuildingsUpgradesValidator,
                          AreaUnitValidator areaUnitValidator,
                          IsPlaceEmptyValidator isPlaceEmptyValidator,
                          BuildNewBuildingTimeGetter timeGetter,
                          TimeValidator timeValidator,
                          BuildingPriceGetter buildingPriceGetter,
                          PaymentExecutor paymentExecutor,
                          BuildNewBuildingWrapperSetter wrapperSetter,
                          BuildNewBuildingEventStarter eventStarter){
        chain = new Chain<>(
                        codeChangingNode,
                        playerRetrievingNode,
                        buildNewBuildingRequestValidator,
                        newBuildingsUpgradesValidator,
                        areaUnitValidator,
                        isPlaceEmptyValidator,
                        timeGetter,
                        timeValidator,
                        buildingPriceGetter,
                        paymentExecutor,
                        wrapperSetter,
                        eventStarter //todo delete it!
        );
    }

    @GetMapping(path = "{code}")
    public StatusResponse get(@PathVariable("code") String code, @RequestBody BuildingRequest request){
        BuildNewBuildingModel model = new BuildNewBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = request;
        chain.executeDefaultOrdered(model);
        return model.RESPONSE;
    }
}
