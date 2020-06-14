package macior.strategygame.service.game.playersControlls;

import executionChains.Chain;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import macior.strategygame.service.playerCommandPipelines.nodes.*;
import macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers.ArmyTransferArmyValidator;
import macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers.ArmyTransferDecreaser;
import macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers.ArmyTransferRequestValidator;
import macior.strategygame.service.playerCommandPipelines.nodes.attacks.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttackService {

    private Chain<ArmyTransferModel> chain;

    @Autowired
    public AttackService(
       CodeChangingNode codeChangingNode,
       PlayerRetrievingNode playerRetrievingNode,
       ArmyTransferRequestValidator requestValidator,
       AreaUnitValidator areaUnitValidator,
       AttackTargetAreaUnitValidator targetAreaUnitValidator,
       AttackPathValidator pathValidator,
       ArmyTransferArmyValidator armyValidator,
       AttackCommanderOwnershipValidator commanderOwnershipValidator,
       AttackTimeGetter timeGetter,
       TimeValidator timeValidator,
       AttackPriceGetter priceGetter,
       PaymentExecutor paymentExecutor,
       ArmyTransferDecreaser armyDecreaser,
       AttackCommanderDecreaser commanderDecreaser,
       AttackEventStarter eventStarter
    ){
        chain = new Chain<>(
                codeChangingNode,
                playerRetrievingNode,
                requestValidator,
                areaUnitValidator,
                targetAreaUnitValidator,
                pathValidator,
                armyValidator,
                commanderOwnershipValidator,
                timeGetter,
                timeValidator,
                priceGetter,
                paymentExecutor,
                armyDecreaser,
                commanderDecreaser,
                eventStarter
        );
    }

    public TimeResponse attack(String code, AttackRequest request){
        //using the same model as for regular army transfer, because I don't need anything more
        ArmyTransferModel model = new ArmyTransferModel();
        model.REQUEST = request;
        model.CODE = code;
        model.RESPONSE = new TimeResponse();
        chain.executeDefaultOrdered(model);
        return (TimeResponse) model.RESPONSE;
    }
}
