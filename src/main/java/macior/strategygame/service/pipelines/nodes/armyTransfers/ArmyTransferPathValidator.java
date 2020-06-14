package macior.strategygame.service.pipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
public class ArmyTransferPathValidator extends ChainNode<ArmyTransferModel> {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Autowired
    private PathValidation pathValidator;

    //checks is tha path is valid, but does not check is the path is the shortest
    //this is tha job for clients part
    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        Location[] path = request.getPath();
        if (!pathValidator.isLongEnough(path)){
            setError(model);
            executor.stop();
            return;
        }

        if (!pathValidator.areEndsCorrect(request, path)){
            setError(model);
            executor.stop();
            return;
        }

        //if all elements belong to the player
        for (Location location : path){
            if (!validator.isLocationOwnedBy(model.PLAYER, location)){
                setError(model);
                executor.stop();
                return;
            }
        }

        //if all elements are neighbours, so they together make a chain
        if (!pathValidator.areAllNeighbours(path)){
            setError(model);
            executor.stop();
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }
}
