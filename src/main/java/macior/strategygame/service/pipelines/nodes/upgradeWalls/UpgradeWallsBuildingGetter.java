package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsBuildingGetter extends ChainNode<UpgradeWallsModel> {

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        AreaUnit unit = model.AREA_UNIT;
        Building building = unit.getWalls();
        if (building == null){
            return;
        }
        if (building.getClass() == UnderConstructionBuilding.class){
            building = ((UnderConstructionBuilding)building).getBuildingUnderConstruction();
        }
        model.BUILDING_UPGRADED = building;
    }
}
