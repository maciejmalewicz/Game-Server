package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsPlaceEmptyValidator extends Node {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        BuildingRequest request = (BuildingRequest)buildingModel.REQUEST;
        Building building = mapper.getBuilding(buildingModel.AREA_UNIT, request.getPlace());
        if (building != null){
            model.RESPONSE.setStatus(GameErrors.BUILDING_PLACE_NOT_EMPTY);
        }
    }
}
