package macior.strategygame.service.executionChain.nodes.commonBattles;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrade;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.RatioSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.BattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackersBonusRatiosGetter extends ExecutionNode <BattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(BattleModel model) {
        appendBonusAttack(model);
        appendBonusDefence(model);
    }

    //from battle formation and commander bot
    private void appendBonusAttack(BattleModel model){
        model.ATTACKERS_BONUS_ATTACK_RATIOS = new RatioSet(); //starting with 1, 1, 1
        PlayersUpgradesSet upgrades = model.ATTACK.ATTACKER.getUpgradesSet();

        //handling battle fromation upgrade
        if (upgrades.upgraded(Upgrades.BATTLE_FORMATION)){

            double bonusPerTank = configuration.getUpgradesConfig()
                    .getArmyUpgradesConfig().getBattleFormation().ATTACK_BONUS_PER_TANK;

            int tanks = model.ATTACK.ARMY.getTanks();
            double cannonsAttackBonus = tanks*bonusPerTank;
            model.ATTACKERS_BONUS_ATTACK_RATIOS.thirdRatio += cannonsAttackBonus;
        }

        //handling commander bonus
        if (model.ATTACK.USING_COMMANDER){
            double bonus = configuration.getMainConfig().COMMANDER_BOT_ATTACK_BONUS;
            model.ATTACKERS_BONUS_ATTACK_RATIOS.increaseRatios(bonus);
        }
    }

    //from mobility
    private void appendBonusDefence(BattleModel model){
        model.ATTACKERS_BONUS_DEFENCE_RATIOS = new RatioSet(); //starting 1, 1, 1
        PlayersUpgradesSet upgrades = model.ATTACK.ATTACKER.getUpgradesSet();

        if (upgrades.upgraded(Upgrades.MOBILITY)){
            double bonus = configuration.getUpgradesConfig().getArmyUpgradesConfig()
                    .getMobility().HITPOINT_BONUS;
            model.ATTACKERS_BONUS_DEFENCE_RATIOS.secondRatio += bonus;
        }
    }
}
