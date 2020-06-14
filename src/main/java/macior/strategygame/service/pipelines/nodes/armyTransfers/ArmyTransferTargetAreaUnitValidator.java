package macior.strategygame.service.pipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferTargetAreaUnitValidator extends ChainNode<ArmyTransferModel> {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        Location targetLocation = request.getTargetLocation();

        model.TARGET_AREA_UNIT = model.PLAYER.getGame().getBoard()
                .getAreaUnit(targetLocation);

        if (!validator.isAreaOwnedBy(model.PLAYER, model.TARGET_AREA_UNIT)){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
        }
    }
}
