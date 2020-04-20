package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.chainOfResponsibility.ChainOfResponsibility;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.*;
import macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding.*;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
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
