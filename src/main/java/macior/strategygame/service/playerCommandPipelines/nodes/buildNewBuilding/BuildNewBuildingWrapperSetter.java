package macior.strategygame.service.playerCommandPipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.playerCommandPipelines.models.BuildNewBuildingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingWrapperSetter extends ChainNode<BuildNewBuildingModel> {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        BuildingRequest request = (BuildingRequest) model.REQUEST;
        Player player = model.PLAYER;

        SmallBuilding beingBuilt = mapper.getSmallBuilding(request.getBuilding());
        AreaUnit areaUnit = player.getGame().getBoard().getAreaUnit(request.getLocation());
        UnderConstructionBuilding wrapper =
                new UnderConstructionBuilding(beingBuilt, areaUnit, request.getPlace());
        //where and what is being added
        areaUnit.setBuilding(request.getPlace(), wrapper);
        model.UNDER_CONSTRUCTION_BUILDING = wrapper;
    }
}
