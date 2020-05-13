package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.game.MainConfiguration.MainConfig;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackTimeGetter extends Node {

    @Autowired
    private MainConfig configuration;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest request = (AttackRequest)transferModel.REQUEST;
        TimeManager timeManager = transferModel.PLAYER.getGame().getTimeManager();
        int pathLength = request.getPath().length-1;

        int duration = pathLength*configuration.MARCHING_TIME;
        duration += configuration.ATTACKING_TIME;

        transferModel.FINISHING_TIME = timeManager.getPostponedEventTime(duration);

        TimeResponse timeResponse = (TimeResponse)transferModel.RESPONSE;
        timeResponse.setFinishingTime(transferModel.FINISHING_TIME);
    }
}
