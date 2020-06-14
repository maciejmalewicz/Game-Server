package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BuildingPriceGetter extends Node {


    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        BuildingRequest request = (BuildingRequest) buildingModel.REQUEST;

        BuildingConfig config = mapper.getConfiguration(request.getBuilding());
        ResourceSet price = config.getCost(1);
        buildingModel.PRICE =  price.canPurchase(buildingModel.PLAYER);
    }
}
