package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class RequestsValidator extends Node{


    @Override
    protected Node getNext(ChainModel model) {
        return null;
    }

    @Override
    protected void applyChanges(ChainModel model) {
        if (model.getClass() == BuildNewBuildingModel.class){
            validateBuildNewBuildingRequest(((BuildNewBuildingModel)model).REQUEST, model);
            System.out.println();
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
    }

    private void validateBuildNewBuildingRequest(BuildingRequest request, ChainModel model){
        if (request == null){
            setError(model);
            return;
        }
        if (request.getLocation() == null){
            setError(model);
            return;
        }
        if (request.getPlace() > 5 || request.getPlace() < 2){
            setError(model);
            return;
        }
        if (request.getBuilding() < 1 || request.getBuilding() > 7){
            setError(model);
            return;
        }
    }
}
