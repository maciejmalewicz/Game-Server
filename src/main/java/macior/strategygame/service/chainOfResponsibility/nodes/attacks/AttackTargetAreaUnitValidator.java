package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackTargetAreaUnitValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest request = (AttackRequest)transferModel.REQUEST;

        transferModel.TARGET_AREA_UNIT = transferModel.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getTargetLocation());

        if (validator.isAreaOwnedBy(transferModel.PLAYER, transferModel.TARGET_AREA_UNIT)){
            model.RESPONSE.setStatus(GameErrors.ATTACKING_OWN_AREA);
        }
    }
}
