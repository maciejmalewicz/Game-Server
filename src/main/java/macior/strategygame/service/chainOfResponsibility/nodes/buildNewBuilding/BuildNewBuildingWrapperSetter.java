package macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingWrapperSetter extends Node {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        BuildingRequest request = buildingModel.REQUEST;
        Player player = buildingModel.PLAYER;

        SmallBuilding beingBuilt = mapper.getSmallBuilding(request.getBuilding());
        AreaUnit areaUnit = player.getGame().getBoard().getAreaUnit(request.getLocation());
        UnderConstructionBuilding wrapper =
                new UnderConstructionBuilding(beingBuilt, areaUnit, request.getPlace());
        //where and what is being added
        areaUnit.setBuilding(request.getPlace(), wrapper);
        buildingModel.UNDER_CONSTRUCTION_BUILDING = wrapper;
    }

}
