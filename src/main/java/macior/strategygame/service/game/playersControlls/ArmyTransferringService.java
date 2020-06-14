package macior.strategygame.service.game.playersControlls;

import executionChains.Chain;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.ChainOfResponsibility;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.nodes.*;
import macior.strategygame.service.pipelines.nodes.armyTransfers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmyTransferringService {

    private Chain<ArmyTransferModel> chain;

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
        chain = new Chain<ArmyTransferModel>(
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
        );
    }

    public TimeResponse transferArmy(String code, ArmyTransferRequest request){
        ArmyTransferModel model = new ArmyTransferModel();
        model.REQUEST = request;
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        chain.executeDefaultOrdered(model);
        return (TimeResponse) model.RESPONSE;
    }
}
