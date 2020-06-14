package macior.strategygame.service.battlePipelines.nodes.commonBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DamageRatioGetter extends ChainNode<BattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(BattleModel model, ChainExecutor executor) {
        double fraction = configuration.getMainConfig().DAMAGE_CURVATURE_COEFFICIENT;
        model.DAMAGE_RATIO = model.BATTLE_RESULT*(Math.pow(fraction, 1-model.BATTLE_RESULT));
    }
}
