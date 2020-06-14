package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.ChainOfResponsibility;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.nodes.*;
import macior.strategygame.service.pipelines.nodes.buildNewBuilding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildNewBuildingService {

    private ChainOfResponsibility chain;

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
        chain = new ChainOfResponsibility(
                new Node[]{
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
                }
        );
    }

    public TimeResponse buildNew(BuildingRequest request, String code){
        BuildNewBuildingModel model = new BuildNewBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = request;
        return (TimeResponse) chain.execute(model);

    }
}
