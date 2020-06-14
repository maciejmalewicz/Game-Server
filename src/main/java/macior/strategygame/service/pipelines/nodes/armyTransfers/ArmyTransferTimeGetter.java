package macior.strategygame.service.pipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
public class ArmyTransferTimeGetter extends ChainNode<ArmyTransferModel> {

    @Autowired
    private MainConfig configuration;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        int pathLength = request.getPath().length - 1;
        TimeManager timeManager = model.PLAYER.getGame().getTimeManager();

        //per every link between areas we have to wait few seconds
        int marchingDuration = configuration.MARCHING_TIME*pathLength;

        model.FINISHING_TIME = timeManager.getPostponedEventTime(marchingDuration);

        TimeResponse timeResponse = (TimeResponse)model.RESPONSE;
        timeResponse.setFinishingTime(model.FINISHING_TIME);
    }
}
