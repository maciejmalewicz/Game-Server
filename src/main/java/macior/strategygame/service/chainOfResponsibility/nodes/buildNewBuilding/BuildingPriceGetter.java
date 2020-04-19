package macior.strategygame.service.chainOfResponsibility.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.chainOfResponsibility.models.BuildNewBuildingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
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
        BuildingConfig config = mapper.getConfiguration(buildingModel.REQUEST.getBuilding());
        ResourceSet price = config.LEVEL1_COST;
        buildingModel.PRICE =  price.canPurchase(buildingModel.PLAYER);
    }

    private ResourceSet getPrice(Player player, BuildingRequest buildingRequest){
        BuildingConfig config = mapper.getConfiguration(buildingRequest.getBuilding());
        ResourceSet price = config.LEVEL1_COST;
        return price.canPurchase(player);
    }
}
