package macior.strategygame.service.pipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingEventGetter extends ChainNode<ArmyTrainingModel> {

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        ArmyTrainingRequest request = (ArmyTrainingRequest) model.REQUEST;
        model.EVENT = model.AREA_UNIT.getEventsQueue().getLastArmyTrainingEvent(request.unitType);
    }
}
