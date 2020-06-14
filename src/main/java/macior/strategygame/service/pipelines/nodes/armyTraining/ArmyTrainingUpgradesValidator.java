package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingUpgradesValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        ArmyTrainingRequest request = (ArmyTrainingRequest)trainingModel.REQUEST;
        PlayersUpgradesSet upgrades = trainingModel.PLAYER.getUpgradesSet();

        if (request.productionType == 2 && !upgrades.upgraded(Upgrades.ASSEMBLY_LINES)){
            model.RESPONSE.setStatus(GameErrors.ASSEMBLY_LINES_NOT_UPGRADED);
            return;
        }

        if (request.productionType == 3 && !upgrades.upgraded(Upgrades.MASS_PRODUCTION)){
            model.RESPONSE.setStatus(GameErrors.MASS_PRODUCTION_NOT_UPGRADED);
            return;
        }
    }
}
