package macior.strategygame.service.executionChain.nodes.commonBattles;

import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import macior.strategygame.service.executionChain.sharedUtilities.StrengthSetBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersStrengthSetGetter extends ExecutionNode<BattleModel> {

    @Autowired
    private StrengthSetBuilder strengthSetBuilder;

    @Override
    public void execute(BattleModel model) {
        model.ATTACKERS_STRENGTH_SET = strengthSetBuilder.build(model.ATTACK.ARMY);
    }
}
