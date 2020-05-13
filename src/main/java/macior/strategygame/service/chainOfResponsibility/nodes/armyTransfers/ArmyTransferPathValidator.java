package macior.strategygame.service.chainOfResponsibility.nodes.armyTransfers;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferPathValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    //checks is tha path is valid, but does not check is the path is the shortest
    //this is tha job for clients part
    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;
        Location[] path = request.getPath();
        if (path.length < 2){
            setError(model);
            return;
        }

        //if first element of the path isn't sending location
        if (!path[0].equals(request.getLocation())){
            setError(model);
            return;
        }

        //if last element of the past isn't receiving location
        if (!path[path.length-1].equals(request.getTargetLocation())){
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
        for (int i = 0; i < path.length-1; i++){
            Location loc1 = path[i];
            Location loc2 = path[i+1];
            if (!areNeighbours(loc1, loc2)){
                setError(model);
                return;
            }
        }

    }

    //in this case, locations are next to each other if distance between them is equal to 1
    //it's because one difference must be 0 and second must be 1
    private boolean areNeighbours(Location loc1, Location loc2){
        int rowDifference = loc1.getRow() - loc2.getRow();
        int columnDifference = loc1.getColumn() - loc2.getColumn();
        int squareDistance = rowDifference*rowDifference + columnDifference*columnDifference;
        return squareDistance == 1;
    }


    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }
}
