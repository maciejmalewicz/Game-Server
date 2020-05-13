package macior.strategygame.service.game.playersControlls;

import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.chainOfResponsibility.ChainOfResponsibility;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.nodes.*;
import macior.strategygame.service.chainOfResponsibility.nodes.armyTraining.ArmyTrainingEventStarter;
import macior.strategygame.service.chainOfResponsibility.nodes.armyTransfers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmyTransferringService {

    private ChainOfResponsibility chain;

    @Autowired
    public ArmyTransferringService(
       CodeChangingNode codeChangingNode,
       PlayerRetrievingNode playerRetrievingNode,
       ArmyTransferRequestValidator requestValidator,
       AreaUnitValidator areaUnitValidator,
       ArmyTransferTargetAreaUnitValidator armyTransferTargetAreaUnitValidator,
       ArmyTransferPathValidator pathValidator,
       ArmyTransferArmyValidator armyValidator,
       ArmyTransferTimeGetter timeGetter,
       TimeValidator timeValidator,
       ArmyTransferPriceGetter priceGetter,
       PaymentExecutor paymentExecutor,
       ArmyTransferDecreaser decreaser,
       ArmyTransferEventStarter eventStarter
    ){
        chain = new ChainOfResponsibility(new Node[]{
                codeChangingNode,
                playerRetrievingNode,
                requestValidator,
                areaUnitValidator,
                armyTransferTargetAreaUnitValidator,
                pathValidator,
                armyValidator,
                timeGetter,
                timeValidator,
                priceGetter,
                paymentExecutor,
                decreaser,
                eventStarter
        });
    }

    public TimeResponse transferArmy(String code, ArmyTransferRequest request){
        ArmyTransferModel model = new ArmyTransferModel();
        model.REQUEST = request;
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        return (TimeResponse) chain.execute(model);
    }
}
