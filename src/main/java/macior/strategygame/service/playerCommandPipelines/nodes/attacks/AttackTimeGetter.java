package macior.strategygame.service.playerCommandPipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.MainConfiguration.MainConfig;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTransferModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackTimeGetter extends ChainNode<ArmyTransferModel> {

    @Autowired
    private MainConfig configuration;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;
        TimeManager timeManager = model.PLAYER.getGame().getTimeManager();
        int pathLength = request.getPath().length-1;

        int duration = pathLength*configuration.MARCHING_TIME;
        duration += configuration.ATTACKING_TIME;

        model.FINISHING_TIME = timeManager.getPostponedEventTime(duration);

        TimeResponse timeResponse = (TimeResponse)model.RESPONSE;
        timeResponse.setFinishingTime(model.FINISHING_TIME);
    }
}
