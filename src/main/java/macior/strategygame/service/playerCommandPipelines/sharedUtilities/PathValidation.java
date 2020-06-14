package macior.strategygame.service.playerCommandPipelines.sharedUtilities;

import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.playerCommandPipelines.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class PathValidation {

    //in this case, locations are next to each other if distance between them is equal to 1
    //it's because one difference must be 0 and second must be 1
    public boolean areNeighbours(Location loc1, Location loc2){
        int rowDifference = loc1.getRow() - loc2.getRow();
        int columnDifference = loc1.getColumn() - loc2.getColumn();
        int squareDistance = rowDifference*rowDifference + columnDifference*columnDifference;
        return squareDistance == 1;
    }

    //we cant have path of length 1 cuz it makes no sense
    public boolean isLongEnough(Location[] path){
        return path.length >= 2;
    }

    public boolean areEndsCorrect(ArmyTransferRequest request, Location[] path){
        //if first element of the path isn't sending location
        if (!path[0].equals(request.getLocation())){
            return false;
        }

        //if last element of the past isn't receiving location
        if (!path[path.length-1].equals(request.getTargetLocation())){
            return false;
        }

        return true;
    }

    //so they make a real chain
    public boolean areAllNeighbours(Location[] path){
        for (int i = 0; i < path.length-1; i++){
            Location loc1 = path[i];
            Location loc2 = path[i+1];
            if (!areNeighbours(loc1, loc2)){
                return false;
            }
        }
        return true;
    }

    public void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }
}
