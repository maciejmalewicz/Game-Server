package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsLevelsGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        if (wallsModel.BUILDING_UPGRADED == null){
            setFirstLevel(wallsModel);
            return;
        }
        if (wallsModel.LATEST_EVENT == null){
            wallsModel.CURRENT_UPGRADING_LEVEL = wallsModel.BUILDING_UPGRADED.LEVEL;
        } else {
            BuildingConcernedEvent bce = wallsModel.LATEST_EVENT;
            wallsModel.CURRENT_UPGRADING_LEVEL = getLevelFromEvent(bce);
        }

        wallsModel.NEXT_LEVEL = wallsModel.CURRENT_UPGRADING_LEVEL + 1;
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
