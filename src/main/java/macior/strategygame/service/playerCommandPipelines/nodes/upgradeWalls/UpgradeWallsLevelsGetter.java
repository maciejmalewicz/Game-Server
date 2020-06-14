package macior.strategygame.service.playerCommandPipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeWallsModel;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsLevelsGetter extends ChainNode<UpgradeWallsModel> {

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        if (model.BUILDING_UPGRADED == null){
            setFirstLevel(model);
            return;
        }
        if (model.LATEST_EVENT == null){
            model.CURRENT_UPGRADING_LEVEL = model.BUILDING_UPGRADED.LEVEL;
        } else {
            BuildingConcernedEvent bce = model.LATEST_EVENT;
            model.CURRENT_UPGRADING_LEVEL = getLevelFromEvent(bce);
        }

        model.NEXT_LEVEL = model.CURRENT_UPGRADING_LEVEL + 1;
    }

    private int getLevelFromEvent(BuildingConcernedEvent bce){
        if (bce.getClass() == BuildingConstructionEvent.class){
            return 1;
        } else {
            return ((BuildingUpgradeEvent)bce).getLevel();
        }
    }

    private void setFirstLevel(UpgradeWallsModel wallsModel){
        wallsModel.CURRENT_UPGRADING_LEVEL = 0;
        wallsModel.NEXT_LEVEL = 1;
    }


}
