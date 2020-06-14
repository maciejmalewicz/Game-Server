package macior.strategygame.service.playerCommandPipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class AttackCommanderOwnershipValidator extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;
        if (request.usingCommander && !model.PLAYER.hasCommander()){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.NO_COMMANDER);
        }
    }
}
