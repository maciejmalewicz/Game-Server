package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.AreaUnitChangingModel;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        AreaUnitChangingModel buildingModel = (AreaUnitChangingModel) model;
        AreaConcernedRequest request = buildingModel.REQUEST;
        buildingModel.AREA_UNIT = buildingModel.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getLocation());
        if (buildingModel.AREA_UNIT == null){
            setError(buildingModel);
        }
        if (buildingModel.AREA_UNIT.getOwner() != buildingModel.PLAYER){
            setError(buildingModel);
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
    }
}
