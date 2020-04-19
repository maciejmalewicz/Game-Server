package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        BuildingRequest request = buildingModel.REQUEST;
        buildingModel.AREA_UNIT = buildingModel.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getLocation());
        if (buildingModel.AREA_UNIT == null){
            setError(buildingModel);
        }
        if (buildingModel.AREA_UNIT.getOwner() != buildingModel.PLAYER){
            setError(buildingModel);
        }
    }

    private void setError(BuildNewBuildingModel model){
        model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
    }
}
