package macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding;


import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingRequestValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildingRequest request = (BuildingRequest) ((BuildNewBuildingModel)model).REQUEST;
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

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
    }
}
