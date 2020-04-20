package macior.strategygame.service.chainOfResponsibility.nodes.upgradeBuilding;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
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
