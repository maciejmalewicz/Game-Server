package macior.strategygame.service.playerCommandPipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.service.playerCommandPipelines.models.BuildingUpgradingConcernedModel;
import org.springframework.stereotype.Component;

@Component
public class LastEventGetter extends ChainNode<BuildingUpgradingConcernedModel> {

    @Override
    public void execute(BuildingUpgradingConcernedModel model, ChainExecutor executor) {
        Building building = model.BUILDING_UPGRADED;
        AreaUnit unit = model.AREA_UNIT;
        model.LATEST_EVENT = unit.getEventsQueue().getLastEventConcerningBuilding(building);
    }
}
