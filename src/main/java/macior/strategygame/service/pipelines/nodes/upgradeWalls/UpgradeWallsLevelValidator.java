package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsLevelValidator extends ChainNode<UpgradeWallsModel> {

    @Autowired
    private WallsConfig wallsConfig;

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        if (model.NEXT_LEVEL > wallsConfig.MAX_LEVEL){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.LEVEL_TOO_HIGH);
        }
    }
}
