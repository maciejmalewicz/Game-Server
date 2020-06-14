package macior.strategygame.service.battlePipelines.nodes.commonBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.Utilities.RandomGenerator;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersLuckBonusHandler extends ChainNode<BattleModel> {

    @Autowired
    private GameConfiguration config;

    @Override
    public void execute(BattleModel model, ChainExecutor executor) {
        int minAttack = (int)(model.ATTACKERS_ATTACK
                * config.getMainConfig().MIN_LUCK_COEFFICIENT);
        int maxAttack = (int)(model.ATTACKERS_ATTACK
                * config.getMainConfig().MAX_LUCK_COEFFICIENT);

        model.ATTACKERS_ATTACK = RandomGenerator.generateBetween(minAttack, maxAttack);
    }
}
