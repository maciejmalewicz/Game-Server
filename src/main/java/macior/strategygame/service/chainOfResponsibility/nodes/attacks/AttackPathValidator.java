package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.PathValidation;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackPathValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator ownershipValidator;

    @Autowired
    private PathValidation pathValidator;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest request = (AttackRequest)transferModel.REQUEST;
        Location[] path = request.getPath();

        if (!pathValidator.isLongEnough(path)){
            setError(model);
            return;
        }

        if (!pathValidator.areEndsCorrect(request, path)){
            setError(model);
            return;
        }

        for (int i = 0; i < path.length-2; i++){
            if (!ownershipValidator.isLocationOwnedBy(transferModel.PLAYER, path[i])){
                setError(model);
                return;
            }
        }

        if (!pathValidator.areAllNeighbours(path)){
            setError(model);
            return;
        }

    }
    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }
}
