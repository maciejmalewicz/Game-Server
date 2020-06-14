package macior.strategygame.service.pipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingUpgradesValidator extends ChainNode<ArmyTrainingModel> {

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        ArmyTrainingRequest request = (ArmyTrainingRequest)model.REQUEST;
        PlayersUpgradesSet upgrades = model.PLAYER.getUpgradesSet();

        if (request.productionType == 2 && !upgrades.upgraded(Upgrades.ASSEMBLY_LINES)){
            model.RESPONSE.setStatus(GameErrors.ASSEMBLY_LINES_NOT_UPGRADED);
            executor.stop();
            return;
        }

        if (request.productionType == 3 && !upgrades.upgraded(Upgrades.MASS_PRODUCTION)){
            model.RESPONSE.setStatus(GameErrors.MASS_PRODUCTION_NOT_UPGRADED);
            executor.stop();
            return;
        }
    }
}
