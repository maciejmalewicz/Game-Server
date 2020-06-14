package macior.strategygame.service.game.playersControlls;

import executionChains.Chain;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.playerCommandPipelines.nodes.*;
import macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpgradeBuildingService {

    private Chain<UpgradeBuildingModel> chain;

    @Autowired
    public UpgradeBuildingService(CodeChangingNode codeChangingNode,
                                  PlayerRetrievingNode playerRetrievingNode,
                                  UpgradeBuildingRequestValidator requestValidator,
                                  AreaUnitValidator areaUnitValidator,
                                  UpgradedBuildingTargetValidator buildingTargetValidator,
                                  UpgradedBuildingConfigurationGetter configurationGetter,
                                  LastEventGetter lastEventGetter,
                                  UpgradeBuildingLevelsGetter levelsGetter,
                                  UpgradeBuildingLevelValidator levelValidator,
                                  UpgradeBuildingTimeGetter timeGetter,
                                  TimeValidator timeValidator,
                                  UpgradeBuildingPriceGetter priceGetter,
                                  PaymentExecutor paymentExecutor,
                                  UpgradeBuildingEventStarter eventStarter){
        chain = new Chain<>(
                codeChangingNode,
                playerRetrievingNode,
                requestValidator,
                areaUnitValidator,
                buildingTargetValidator,
                configurationGetter,
                lastEventGetter,
                levelsGetter,
                levelValidator,
                timeGetter,
                timeValidator,
                priceGetter,
                paymentExecutor,
                eventStarter
        );
    }

    public TimeResponse upgradeBuilding(String code, UpgradeRequest request) {
        UpgradeBuildingModel model = new UpgradeBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = request;
        chain.executeDefaultOrdered(model);
        return (TimeResponse) model.RESPONSE;
    }

}
