package macior.strategygame.service.game.battles;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.executionChain.ExecutionChain;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleResult;
import macior.strategygame.service.executionChain.nodes.commonBattles.*;
import macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles.*;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnoccupiedFieldBattleService {

    private ExecutionChain<UnoccupiedFieldBattleResult, UnoccupiedFieldBattleModel> chain;

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
        ExecutionNode[] nodes = new ExecutionNode[] {
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
                //todo test!!!
        };
        chain = new ExecutionChain<UnoccupiedFieldBattleResult, UnoccupiedFieldBattleModel>(nodes){

            @Override
            public UnoccupiedFieldBattleResult extractOutput(UnoccupiedFieldBattleModel model){
                UnoccupiedFieldBattleResult result = new UnoccupiedFieldBattleResult();
                result.HAS_ATTACKER_WON = model.ATTACKER_HAS_WON;
                return result;
            }
        };
    }

    public UnoccupiedFieldBattleResult battle(Attack attack){
        UnoccupiedFieldBattleModel model = new UnoccupiedFieldBattleModel();
        model.ATTACK = attack;
        UnoccupiedFieldBattleResult result = chain.execute(model);
        return result;
    }
}
