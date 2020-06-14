package macior.strategygame.service.playerCommandPipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.playerCommandPipelines.models.ArmyTrainingModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingRequestValidator extends ChainNode<ArmyTrainingModel> {

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        if (model.REQUEST == null){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
            return;
        }

        ArmyTrainingRequest request = (ArmyTrainingRequest)model.REQUEST;

        if (request.getLocation() == null){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
            return;
        }

        if (request.productionType < 1 || request.productionType > 3){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
            return;
        }

        if (request.unitType < 1 || request.unitType > 3){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
            executor.stop();
            return;
        }
    }


}
