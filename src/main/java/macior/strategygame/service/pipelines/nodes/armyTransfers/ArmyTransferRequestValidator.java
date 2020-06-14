package macior.strategygame.service.pipelines.nodes.armyTransfers;

import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferRequestValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        if (transferModel.REQUEST == null){
            transferModel.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;

        if (request.getArmy() == null){
            transferModel.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }

        if (request.getTargetLocation() == null || request.getLocation() == null){
            transferModel.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
    }
}
