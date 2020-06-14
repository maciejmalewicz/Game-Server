package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.ChainOfResponsibility;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.nodes.*;
import macior.strategygame.service.pipelines.nodes.armyTraining.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//todo: test and fix eventual bugs, update angular part so it works (maybe test using angular part)
//todo: should work properly
@Service
public class TrainArmyService {

    private ChainOfResponsibility chain;

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

        chain = new ChainOfResponsibility( new Node[]{
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
        });
    }

    public TimeResponse trainArmy(String code, ArmyTrainingRequest request){
        TimeResponse response = new TimeResponse();
        ArmyTrainingModel model = new ArmyTrainingModel();
        model.CODE = code;
        model.REQUEST = request;
        model.RESPONSE = response;
        return (TimeResponse)chain.execute(model);
    }
}
