package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsTimeGetter extends ChainNode<UpgradeWallsModel> {

    @Autowired
    private SmallBuildingsConfig config;

    @Autowired
    private TimeGetter timeGetter;

    @Override
    public void execute(UpgradeWallsModel model, ChainExecutor executor) {
        //to model
        model.FINISHING_TIME = timeGetter.getTime(model, config.getWallsConfig());
        //to response
        TimeResponse timeResponse = (TimeResponse)model.RESPONSE;
        timeResponse.setFinishingTime(model.FINISHING_TIME);
    }
}
