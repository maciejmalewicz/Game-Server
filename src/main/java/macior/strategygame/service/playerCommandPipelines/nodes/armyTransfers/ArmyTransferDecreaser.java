package macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferDecreaser extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        Army toSend = request.getArmy();
        Army currentArmy = model.AREA_UNIT.getArmy();

        //subtracting units from army being on area from which we are sending transfer
        currentArmy.addDroids(-toSend.getDroids());
        currentArmy.addTanks(-toSend.getTanks());
        currentArmy.addCannons(-toSend.getCannons());
    }
}
