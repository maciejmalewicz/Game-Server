package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
public class IsPlaceEmptyValidator extends ChainNode<BuildNewBuildingModel> {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        BuildingRequest request = (BuildingRequest)model.REQUEST;
        Building building = mapper.getBuilding(model.AREA_UNIT, request.getPlace());
        if (building != null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.BUILDING_PLACE_NOT_EMPTY);
        }
    }
}
