package macior.strategygame.service.chainOfResponsibility.nodes.upgradeBuilding;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradedBuildingTargetValidator extends Node {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        UpgradeRequest request = (UpgradeRequest) upgradeModel.REQUEST;
        AreaUnit unit = upgradeModel.AREA_UNIT;

        Building toUpgrade = mapper.getBuilding(unit, request.getPlace());
        if (toUpgrade.getClass() == UnderConstructionBuilding.class){
            toUpgrade = ((UnderConstructionBuilding)toUpgrade).getBuildingUnderConstruction();
        }
        upgradeModel.BUILDING_UPGRADED = toUpgrade;
        if (toUpgrade == null){
            model.RESPONSE.setStatus(GameErrors.NO_BUILDING_FOUND);
        }
    }
}
