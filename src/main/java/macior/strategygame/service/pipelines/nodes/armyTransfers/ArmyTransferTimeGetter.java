package macior.strategygame.service.pipelines.nodes.armyTransfers;

import macior.strategygame.game.MainConfiguration.MainConfig;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferTimeGetter extends Node {

    @Autowired
    private MainConfig configuration;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;
        int pathLength = request.getPath().length - 1;
        TimeManager timeManager = ((ArmyTransferModel) model).PLAYER.getGame().getTimeManager();

        //per every link between areas we have to wait few seconds
        int marchingDuration = configuration.MARCHING_TIME*pathLength;

        transferModel.FINISHING_TIME = timeManager.getPostponedEventTime(marchingDuration);

        TimeResponse timeResponse = (TimeResponse)transferModel.RESPONSE;
        timeResponse.setFinishingTime(transferModel.FINISHING_TIME);
    }
}
