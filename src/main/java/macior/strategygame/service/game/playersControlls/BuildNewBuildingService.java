package macior.strategygame.service.game.playersControlls;

import executionChains.Chain;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.playerCommandPipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.playerCommandPipelines.nodes.*;
import macior.strategygame.service.playerCommandPipelines.nodes.buildNewBuilding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildNewBuildingService {

    private Chain<BuildNewBuildingModel> chain;

    @Autowired
    public BuildNewBuildingService(CodeChangingNode codeChangingNode,
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
                        eventStarter
        );
    }

    public TimeResponse buildNew(BuildingRequest request, String code){
        BuildNewBuildingModel model = new BuildNewBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = request;
        chain.executeDefaultOrdered(model);
        return (TimeResponse) model.RESPONSE;

    }
}
