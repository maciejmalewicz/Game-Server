package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.chainOfResponsibility.ChainOfResponsibility;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeWallsModel;
import macior.strategygame.service.chainOfResponsibility.nodes.*;
import macior.strategygame.service.chainOfResponsibility.nodes.upgradeWalls.*;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpgradeWallsService {

    @Autowired
    private UserValidationService validationService;

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private GameConfiguration configuration;

    private ChainOfResponsibility chain;

    @Autowired
    public UpgradeWallsService(CodeChangingNode codeChangingNode,
                               PlayerRetrievingNode playerRetrievingNode,
                               UpgradeWallsRequestValidator requestValidator,
                               AreaUnitValidator areaUnitValidator,
                               UpgradeWallsBuildingGetter buildingGetter,
                               LastEventGetter lastEventGetter,
                               UpgradeWallsLevelsGetter levelsGetter,
                               UpgradeWallsLevelValidator levelValidator,
                               UpgradeWallsTimeGetter timeGetter,
                               TimeValidator timeValidator,
                               UpgradeWallsPriceGetter priceGetter,
                               PaymentExecutor paymentExecutor,
                               UpgradeWallsEventStarter eventStarter
                               //todo rest of this
                               ){
        chain = new ChainOfResponsibility(new Node[]{
                codeChangingNode,
                playerRetrievingNode,
                requestValidator,
                areaUnitValidator,
                buildingGetter,
                lastEventGetter,
                levelsGetter,
                levelValidator,
                timeGetter,
                timeValidator,
                priceGetter,
                paymentExecutor,
                eventStarter
        });

    }

    public TimeResponse upgradeWalls(String code, AreaConcernedRequest request){

        UpgradeWallsModel model = new UpgradeWallsModel();
        model.CODE = code;
        model.REQUEST = request;
        model.RESPONSE = new TimeResponse();
        return (TimeResponse) chain.execute(model);
    }
}
