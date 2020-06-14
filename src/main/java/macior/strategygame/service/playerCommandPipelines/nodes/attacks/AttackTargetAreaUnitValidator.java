package macior.strategygame.service.playerCommandPipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import macior.strategygame.service.playerCommandPipelines.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackTargetAreaUnitValidator extends ChainNode<ArmyTransferModel> {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;

        model.TARGET_AREA_UNIT = model.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getTargetLocation());

        if (validator.isAreaOwnedBy(model.PLAYER, model.TARGET_AREA_UNIT)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.ATTACKING_OWN_AREA);
        }
    }
}
