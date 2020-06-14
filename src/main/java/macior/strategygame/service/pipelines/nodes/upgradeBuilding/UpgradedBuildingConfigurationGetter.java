package macior.strategygame.service.pipelines.nodes.upgradeBuilding;

import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradedBuildingConfigurationGetter extends Node {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        upgradeModel.BUILDING_CONFIG = mapper.getConfiguration(upgradeModel.BUILDING_UPGRADED);
    }
}
