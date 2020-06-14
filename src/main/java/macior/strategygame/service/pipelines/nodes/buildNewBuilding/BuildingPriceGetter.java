package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
public class BuildingPriceGetter extends ChainNode<BuildNewBuildingModel> {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        BuildingRequest request = (BuildingRequest) model.REQUEST;

        BuildingConfig config = mapper.getConfiguration(request.getBuilding());
        ResourceSet price = config.getCost(1);
        model.PRICE =  price.canPurchase(model.PLAYER);
    }
}
