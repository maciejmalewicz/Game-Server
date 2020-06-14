package macior.strategygame.service.battlePipelines.nodes.commonBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Army;
import macior.strategygame.game.RatioSet;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import macior.strategygame.service.battlePipelines.sharedUtilities.ArmyDamageDealer;
import macior.strategygame.service.battlePipelines.sharedUtilities.DamageRatiosCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersArmyDamageDealer extends ChainNode<BattleModel> {

    @Autowired
    private DamageRatiosCalculator ratiosCalculator;

    @Autowired
    private ArmyDamageDealer armyDamageDealer;

    @Override
    public void execute(BattleModel model, ChainExecutor executor) {
        if (!model.ATTACKER_HAS_WON){ //if attacker lost, the whole army is being destroyed
            model.ATTACK.ARMY = new Army();
        } else {
            damageArmy(model);
        }
    }

    private void damageArmy(BattleModel model){
        RatioSet defenceRatios = ratiosCalculator.getDefenceRatios(model.ATTACKERS_STRENGTH_SET);
        RatioSet damageRatios = ratiosCalculator.getDamageRatios(defenceRatios, model.DAMAGE_RATIO);
        armyDamageDealer.damageArmy(model.ATTACK.ARMY, damageRatios);
    }
}
