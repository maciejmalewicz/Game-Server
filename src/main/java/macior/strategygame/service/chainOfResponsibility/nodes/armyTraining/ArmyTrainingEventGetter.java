package macior.strategygame.service.chainOfResponsibility.nodes.armyTraining;

import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTrainingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingEventGetter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        trainingModel.EVENT = trainingModel.AREA_UNIT.getEventsQueue().getLastArmyTrainingEvent();
    }
}
