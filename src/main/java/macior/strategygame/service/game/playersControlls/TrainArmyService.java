package macior.strategygame.service.game.playersControlls;

import executionChains.Chain;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTrainingModel;
import macior.strategygame.service.playerCommandPipelines.nodes.*;
import macior.strategygame.service.playerCommandPipelines.nodes.armyTraining.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainArmyService {

    private Chain<ArmyTrainingModel> chain;

    @Autowired
    public TrainArmyService(CodeChangingNode codeChangingNode,
                            PlayerRetrievingNode playerRetrievingNode,
                            ArmyTrainingRequestValidator armyTrainingRequestValidator,
                            AreaUnitValidator areaUnitValidator,
                            ArmyTrainingBuildingGetter buildingGetter,
                            ArmyTrainingUpgradesValidator upgradesValidator,
                            ArmyTrainingConfigurationGetter configurationGetter,
                            ArmyTrainingPriceGetter priceGetter,
                            ArmyTrainingEventGetter eventGetter,
                            ArmyTrainingTimeGetter timeGetter,
                            TimeValidator timeValidator,
                            PaymentExecutor paymentExecutor,
                            ArmyTrainingEventStarter eventStarter
                            ){

        chain = new Chain<>(
                codeChangingNode,
                playerRetrievingNode,
                armyTrainingRequestValidator,
                areaUnitValidator,
                buildingGetter,
                upgradesValidator,
                configurationGetter,
                priceGetter,
                eventGetter,
                timeGetter,
                timeValidator,
                paymentExecutor,
                eventStarter
        );
    }

    public TimeResponse trainArmy(String code, ArmyTrainingRequest request){
        TimeResponse response = new TimeResponse();
        ArmyTrainingModel model = new ArmyTrainingModel();
        model.CODE = code;
        model.REQUEST = request;
        model.RESPONSE = response;
        chain.executeDefaultOrdered(model);
        return (TimeResponse)model.RESPONSE;
    }
}
