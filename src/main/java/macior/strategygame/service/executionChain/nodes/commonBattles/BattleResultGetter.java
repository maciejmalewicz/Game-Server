package macior.strategygame.service.executionChain.nodes.commonBattles;

import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.stereotype.Component;

@Component
public class BattleResultGetter extends ExecutionNode<BattleModel> {

    @Override
    public void execute(BattleModel model) {
        double attackersHits = ((double)model.DEFENDERS_DEFENCE)/((double)model.ATTACKERS_ATTACK);
        double defendersHits = ((double)model.ATTACKERS_DEFENCE)/((double)model.DEFENDERS_ATTACK);

        if (attackersHits < defendersHits){
            model.ATTACKER_HAS_WON = true;
        } else {
            model.ATTACKER_HAS_WON = false;
        }

        if (model.ATTACKER_HAS_WON){
            model.BATTLE_RESULT = attackersHits/defendersHits;
        } else {
            model.BATTLE_RESULT = defendersHits/attackersHits;
        }
    }
}
