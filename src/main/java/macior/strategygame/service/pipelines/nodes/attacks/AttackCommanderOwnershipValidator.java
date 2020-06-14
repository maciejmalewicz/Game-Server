package macior.strategygame.service.pipelines.nodes.attacks;

import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class AttackCommanderOwnershipValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest request = (AttackRequest)transferModel.REQUEST;
        if (request.usingCommander && !transferModel.PLAYER.hasCommander()){
            model.RESPONSE.setStatus(GameErrors.NO_COMMANDER);
        }
    }
}
