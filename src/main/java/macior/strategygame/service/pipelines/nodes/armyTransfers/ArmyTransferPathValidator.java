package macior.strategygame.service.pipelines.nodes.armyTransfers;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.pipelines.sharedUtilities.PathValidation;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferPathValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Autowired
    private PathValidation pathValidator;

    //checks is tha path is valid, but does not check is the path is the shortest
    //this is tha job for clients part
    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;
        Location[] path = request.getPath();
        if (!pathValidator.isLongEnough(path)){
            setError(model);
            return;
        }

        if (!pathValidator.areEndsCorrect(request, path)){
            setError(model);
            return;
        }

        //if all elements belong to the player
        for (Location location : path){
            if (!validator.isLocationOwnedBy(transferModel.PLAYER, location)){
                setError(model);
                return;
            }
        }

        //if all elements are neighbours, so they together make a chain
        if (!pathValidator.areAllNeighbours(path)){
            setError(model);
            return;
        }
    }


    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }
}
