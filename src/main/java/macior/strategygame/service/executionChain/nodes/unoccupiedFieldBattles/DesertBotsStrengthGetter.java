package macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles;

import macior.strategygame.game.BattlesManagement.configuration.DesertBotsConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesertBotsStrengthGetter extends ExecutionNode<UnoccupiedFieldBattleModel> {

    @Autowired
    private DesertBotsConfig configuration;

    @Override
    public void execute(UnoccupiedFieldBattleModel model) {
        model.DEFENDERS_ATTACK = model.DESERT_BOTS*configuration.ATTACK;
        model.DEFENDERS_BASE_DEFENCE = model.DESERT_BOTS*configuration.DEFENCE;

        model.DEFENDERS_DEFENCE = model.DEFENDERS_BASE_DEFENCE;
    }
}
