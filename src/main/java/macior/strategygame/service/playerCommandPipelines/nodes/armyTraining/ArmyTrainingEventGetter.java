package macior.strategygame.service.playerCommandPipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTrainingModel;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingEventGetter extends ChainNode<ArmyTrainingModel> {

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        ArmyTrainingRequest request = (ArmyTrainingRequest) model.REQUEST;
        model.EVENT = model.AREA_UNIT.getEventsQueue().getLastArmyTrainingEvent(request.unitType);
    }
}
