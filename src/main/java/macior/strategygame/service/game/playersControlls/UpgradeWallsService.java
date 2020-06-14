package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.pipelines.ChainOfResponsibility;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.*;
import macior.strategygame.service.pipelines.nodes.upgradeWalls.*;
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
