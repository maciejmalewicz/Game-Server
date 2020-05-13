package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class AttackCommanderDecreaser extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest request = (AttackRequest)transferModel.REQUEST;

        if (request.usingCommander){
            transferModel.PLAYER.setCommander(false);
        }
    }
}
