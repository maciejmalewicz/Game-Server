package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingRequestValidator extends Node {


    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;

        if (trainingModel.REQUEST == null){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            return;
        }

        ArmyTrainingRequest request = (ArmyTrainingRequest)trainingModel.REQUEST;

        if (request.getLocation() == null){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            return;
        }

        if (request.productionType < 1 || request.productionType > 3){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            return;
        }

        if (request.unitType < 1 || request.unitType > 3){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            return;
        }
    }
}
