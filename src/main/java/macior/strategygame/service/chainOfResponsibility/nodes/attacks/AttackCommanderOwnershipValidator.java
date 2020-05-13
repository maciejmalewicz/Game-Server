package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
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
