package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.pipelines.ChainOfResponsibility;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.*;
import macior.strategygame.service.pipelines.nodes.upgradeBuilding.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpgradeBuildingService {

    private ChainOfResponsibility chain;

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
        chain = new ChainOfResponsibility(new Node[]{
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
        });
    }

    public TimeResponse upgradeBuilding(String code, UpgradeRequest request) {
        UpgradeBuildingModel model = new UpgradeBuildingModel();
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        model.REQUEST = request;
        return (TimeResponse) chain.execute(model);
    }

}
