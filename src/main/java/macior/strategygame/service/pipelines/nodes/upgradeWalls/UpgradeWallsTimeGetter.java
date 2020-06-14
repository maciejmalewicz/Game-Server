package macior.strategygame.service.pipelines.nodes.upgradeWalls;

import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeWallsModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsTimeGetter extends Node {

    @Autowired
    private SmallBuildingsConfig config;

    @Autowired
    private TimeGetter timeGetter;

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        //to model
        wallsModel.FINISHING_TIME = timeGetter.getTime(wallsModel, config.getWallsConfig());
        //to response
        TimeResponse timeResponse = (TimeResponse)wallsModel.RESPONSE;
        timeResponse.setFinishingTime(wallsModel.FINISHING_TIME);
    }
}
