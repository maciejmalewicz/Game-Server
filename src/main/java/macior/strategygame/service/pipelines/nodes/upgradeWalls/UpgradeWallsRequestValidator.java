package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsRequestValidator extends ChainNode<UpgradeWallsModel> {

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        AreaConcernedRequest request = model.REQUEST;
        if (request == null || request.getLocation() == null){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
    }
}
