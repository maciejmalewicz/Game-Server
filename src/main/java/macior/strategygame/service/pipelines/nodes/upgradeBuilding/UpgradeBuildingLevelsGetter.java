package macior.strategygame.service.pipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingLevelsGetter extends ChainNode<UpgradeBuildingModel> {

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        Building building = model.BUILDING_UPGRADED;
        BuildingConcernedEvent event = model.LATEST_EVENT;
        //unpack wrapper if building hasn't been built yet
        model.CURRENT_UPGRADING_LEVEL = getCurrentUpgradingLevel(building, event);
        model.NEXT_LEVEL = model.CURRENT_UPGRADING_LEVEL + 1;
    }

    private int getCurrentUpgradingLevel(Building building, BuildingConcernedEvent event){
        //unpack wrapper if building hasn't been built yet
        if (building.getClass() == UnderConstructionBuilding.class){
            UnderConstructionBuilding ucb = (UnderConstructionBuilding)building;
            building = ucb.getBuildingUnderConstruction();
        }
        //if nothing is happening with the building, just return it's level
        if (event == null){
            return building.LEVEL;
        }
        if (event.getClass() == BuildingConstructionEvent.class){
            return 1;
        }
        if (event.getClass() == BuildingUpgradeEvent.class){
            BuildingUpgradeEvent upgradeEvent = (BuildingUpgradeEvent)event;
            return upgradeEvent.getLevel();
        }
        return -1;
    }


}
