package macior.strategygame.service.chainOfResponsibility.nodes.upgradeWalls;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.models.game.configuration.SmallBuildingsConfig;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeWallsModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.TimeGetter;
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
