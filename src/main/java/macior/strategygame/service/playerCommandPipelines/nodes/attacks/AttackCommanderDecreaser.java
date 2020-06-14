package macior.strategygame.service.playerCommandPipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import org.springframework.stereotype.Component;

@Component
public class AttackCommanderDecreaser extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;

        if (request.usingCommander){
            model.PLAYER.setCommander(false);
        }
    }
}
