package macior.strategygame.service.playerCommandPipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferRequestValidator extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        if (model.REQUEST == null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;

        if (request.getArmy() == null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }

        if (request.getTargetLocation() == null || request.getLocation() == null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
    }
}
