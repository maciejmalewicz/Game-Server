package macior.strategygame.service.chainOfResponsibility.nodes.upgradeWalls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeWallsModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsBuildingGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        AreaUnit unit = wallsModel.AREA_UNIT;
        Building building = unit.getWalls();
        if (building == null){
            return;
        }
        if (building.getClass() == UnderConstructionBuilding.class){
            building = ((UnderConstructionBuilding)building).getBuildingUnderConstruction();
        }
        wallsModel.BUILDING_UPGRADED = building;
    }
}
