package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsLevelValidator extends Node {

    @Autowired
    private WallsConfig wallsConfig;

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        if (wallsModel.NEXT_LEVEL > wallsConfig.MAX_LEVEL){
            wallsModel.RESPONSE.setStatus(GameErrors.LEVEL_TOO_HIGH);
        }
    }
}
