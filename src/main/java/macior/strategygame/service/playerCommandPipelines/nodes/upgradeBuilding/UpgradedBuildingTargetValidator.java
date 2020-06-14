package macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.game.playersControlls.BuildingsPlacesMapperService;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradedBuildingTargetValidator extends ChainNode<UpgradeBuildingModel> {

    @Autowired
    private BuildingsPlacesMapperService mapper;

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        UpgradeRequest request = (UpgradeRequest) model.REQUEST;
        AreaUnit unit = model.AREA_UNIT;

        Building toUpgrade = mapper.getBuilding(unit, request.getPlace());
        if (toUpgrade == null){
            model.RESPONSE.setStatus(GameErrors.NO_BUILDING_FOUND);
            executor.stop();
            return;
        }
        if (toUpgrade.getClass() == UnderConstructionBuilding.class){
            toUpgrade = ((UnderConstructionBuilding)toUpgrade).getBuildingUnderConstruction();
        }
        model.BUILDING_UPGRADED = toUpgrade;
        if (toUpgrade == null){
            model.RESPONSE.setStatus(GameErrors.NO_BUILDING_FOUND);
            executor.stop();
        }
    }
}
