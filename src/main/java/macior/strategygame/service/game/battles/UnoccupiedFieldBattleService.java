package macior.strategygame.service.game.battles;

import executionChains.Chain;
import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.service.battlePipelines.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.battlePipelines.models.UnoccupiedFieldBattleResult;
import macior.strategygame.service.battlePipelines.nodes.commonBattles.*;
import macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles.DesertBotsGenerator;
import macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles.DesertBotsStrengthGetter;
import macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles.MinesweepersBonusHandler;
import macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles.UnoccupiedAreaUnitChanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnoccupiedFieldBattleService {

    private Chain<UnoccupiedFieldBattleModel> chain;

    @Autowired
    public UnoccupiedFieldBattleService(
            DesertBotsGenerator desertBotsGenerator,
            AttackersStrengthSetGetter attackersStrengthSetGetter,
            DesertBotsStrengthGetter desertBotsStrengthGetter,
            AttackersBonusRatiosGetter attackersBonusRatiosGetter,
            AttackersBaseAttributesGenerator attackersBaseAttributesGenerator,
            MinesweepersBonusHandler minesweepersBonusHandler,
            AttackersLuckBonusHandler luckBonusHandler,
            BattleResultGetter battleResultGetter,
            DamageRatioGetter damageRatioGetter,
            AttackersArmyDamageDealer attackersArmyDamageDealer,
            UnoccupiedAreaUnitChanger areaUnitChanger
    ){
        chain = new Chain<>(
                desertBotsGenerator,
                attackersStrengthSetGetter,
                desertBotsStrengthGetter,
                attackersBonusRatiosGetter,
                attackersBaseAttributesGenerator,
                minesweepersBonusHandler,
                luckBonusHandler,
                battleResultGetter,
                damageRatioGetter,
                attackersArmyDamageDealer,
                areaUnitChanger
        );
    }

    public UnoccupiedFieldBattleResult battle(Attack attack){
        UnoccupiedFieldBattleModel model = new UnoccupiedFieldBattleModel();
        model.ATTACK = attack;
        chain.executeDefaultOrdered(model);
        return extractOutput(model);
    }

    private UnoccupiedFieldBattleResult extractOutput(UnoccupiedFieldBattleModel model){
        UnoccupiedFieldBattleResult result = new UnoccupiedFieldBattleResult();
        result.HAS_ATTACKER_WON = model.ATTACKER_HAS_WON;
        return result;
    }
}
