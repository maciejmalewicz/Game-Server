package macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradedBuildingConfigurationGetter extends ChainNode<UpgradeBuildingModel> {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        model.BUILDING_CONFIG = mapper.getConfiguration(model.BUILDING_UPGRADED);
    }
}
