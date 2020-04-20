package macior.strategygame.service.chainOfResponsibility.nodes;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.service.chainOfResponsibility.models.BuildingUpgradingConcernedModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class LastEventGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildingUpgradingConcernedModel upgradeModel = (BuildingUpgradingConcernedModel) model;
        Building building = upgradeModel.BUILDING_UPGRADED;
        AreaUnit unit = upgradeModel.AREA_UNIT;
        upgradeModel.LATEST_EVENT = unit.getBuildingQueue().getLastEventConcerningBuilding(building);
    }
}
