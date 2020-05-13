package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.AreaUnitChangingModel;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitValidator extends Node {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void applyChanges(ChainModel model) {
        AreaUnitChangingModel buildingModel = (AreaUnitChangingModel) model;
        AreaConcernedRequest request = buildingModel.REQUEST;

        buildingModel.AREA_UNIT = buildingModel.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getLocation());

        if (!validator.isAreaOwnedBy(buildingModel.PLAYER, buildingModel.AREA_UNIT)){
            setError(buildingModel);
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
    }
}
