package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingEventGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        ArmyTrainingRequest request = (ArmyTrainingRequest) trainingModel.REQUEST;
        trainingModel.EVENT = trainingModel.AREA_UNIT.getEventsQueue().getLastArmyTrainingEvent(request.unitType);
    }
}
