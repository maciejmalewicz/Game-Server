package macior.strategygame.service.pipelines.nodes.armyTransfers;

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
public class ArmyTransferTargetAreaUnitValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;
        Location targetLocation = request.getTargetLocation();

        transferModel.TARGET_AREA_UNIT = transferModel.PLAYER.getGame().getBoard()
                .getAreaUnit(targetLocation);

        if (!validator.isAreaOwnedBy(transferModel.PLAYER, transferModel.TARGET_AREA_UNIT)){
            model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
        }
    }
}
