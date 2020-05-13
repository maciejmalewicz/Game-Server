package macior.strategygame.service.executionChain.nodes.commonBattles;


import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.stereotype.Component;

@Component
public class AttackersBaseAttributesGenerator extends ExecutionNode<BattleModel> {

    @Override
    public void execute(BattleModel model) {
        appendAttackersBaseAttack(model);
        appendAttackersBaseDefence(model);
    }

    private void appendAttackersBaseAttack(BattleModel model){
        double droidsAttack = model.ATTACKERS_STRENGTH_SET.DROIDS_ATTACK
                * model.ATTACKERS_BONUS_ATTACK_RATIOS.firstRatio;

        double tanksAttack = model.ATTACKERS_STRENGTH_SET.TANKS_ATTACK
                * model.ATTACKERS_BONUS_ATTACK_RATIOS.secondRatio;

        double cannonsAttack = model.ATTACKERS_STRENGTH_SET.CANNONS_ATTACK
                * model.ATTACKERS_BONUS_ATTACK_RATIOS.thirdRatio;

        model.ATTACKERS_ATTACK = (int)(droidsAttack + tanksAttack + cannonsAttack);
    }

    private void appendAttackersBaseDefence(BattleModel model){
        double droidsDefence = model.ATTACKERS_STRENGTH_SET.DROIDS_DEFENCE
                * model.ATTACKERS_BONUS_DEFENCE_RATIOS.firstRatio;

        double tanksDefence = model.ATTACKERS_STRENGTH_SET.DROIDS_DEFENCE
                * model.ATTACKERS_BONUS_DEFENCE_RATIOS.secondRatio;

        double cannonsDefence = model.ATTACKERS_STRENGTH_SET.CANNONS_DEFENCE
                * model.ATTACKERS_BONUS_DEFENCE_RATIOS.thirdRatio;

        model.ATTACKERS_BASE_DEFENCE = (int)(droidsDefence + tanksDefence + cannonsDefence);
    }
}
