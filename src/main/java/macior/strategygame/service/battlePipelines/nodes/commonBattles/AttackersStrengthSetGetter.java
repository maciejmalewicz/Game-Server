package macior.strategygame.service.battlePipelines.nodes.commonBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import macior.strategygame.service.battlePipelines.sharedUtilities.StrengthSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersStrengthSetGetter extends ChainNode<BattleModel> {

    @Autowired
    private StrengthSetBuilder strengthSetBuilder;

    @Override
    public void execute(BattleModel model, ChainExecutor executor) {
        model.ATTACKERS_STRENGTH_SET = strengthSetBuilder.build(model.ATTACK.ARMY);
    }
}
