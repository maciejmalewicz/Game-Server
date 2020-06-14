package macior.strategygame.service.pipelines.nodes;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.service.pipelines.models.BuildingUpgradingConcernedModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import org.springframework.stereotype.Component;

@Component
public class LastEventGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildingUpgradingConcernedModel upgradeModel = (BuildingUpgradingConcernedModel) model;
        Building building = upgradeModel.BUILDING_UPGRADED;
        AreaUnit unit = upgradeModel.AREA_UNIT;
        upgradeModel.LATEST_EVENT = unit.getEventsQueue().getLastEventConcerningBuilding(building);
    }
}
