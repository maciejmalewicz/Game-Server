package macior.strategygame.service.executionChain.nodes.commonBattles;

import macior.strategygame.game.MainConfiguration.MainConfig;
import macior.strategygame.game.Utilities.RandomGenerator;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersLuckBonusHandler extends ExecutionNode<BattleModel> {

    @Autowired
    private GameConfiguration config;

    @Override
    public void execute(BattleModel model) {
        int minAttack = (int)(model.ATTACKERS_ATTACK
                * config.getMainConfig().MIN_LUCK_COEFFICIENT);
        int maxAttack = (int)(model.ATTACKERS_ATTACK
                * config.getMainConfig().MAX_LUCK_COEFFICIENT);

        model.ATTACKERS_ATTACK = RandomGenerator.generateBetween(minAttack, maxAttack);
    }
}
