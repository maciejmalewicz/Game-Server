package macior.strategygame.service.executionChain.nodes.commonBattles;

import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DamageRatioGetter extends ExecutionNode<BattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(BattleModel model) {
        double fraction = configuration.getMainConfig().DAMAGE_CURVATURE_COEFFICIENT;
        model.DAMAGE_RATIO = model.BATTLE_RESULT*(Math.pow(fraction, 1-model.BATTLE_RESULT));
        System.out.println();
    }
}
