package macior.strategygame.service.game.battles;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.executionChain.ExecutionChain;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleResult;
import macior.strategygame.service.executionChain.nodes.commonBattles.AttackersBaseAttributesGenerator;
import macior.strategygame.service.executionChain.nodes.commonBattles.AttackersBonusRatiosGetter;
import macior.strategygame.service.executionChain.nodes.commonBattles.AttackersStrengthSetGetter;
import macior.strategygame.service.executionChain.nodes.commonBattles.BattleResultGetter;
import macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles.DesertBotsGenerator;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles.DesertBotsStrengthGetter;
import macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles.MinesweepersBonusHandler;
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
            BattleResultGetter battleResultGetter
    ){
        ExecutionNode[] nodes = new ExecutionNode[] {
                desertBotsGenerator,
                attackersStrengthSetGetter,
                desertBotsStrengthGetter,
                attackersBonusRatiosGetter,
                attackersBaseAttributesGenerator,
                minesweepersBonusHandler,
                battleResultGetter
        };
        chain = new ExecutionChain<UnoccupiedFieldBattleResult, UnoccupiedFieldBattleModel>(nodes){

            @Override
            public UnoccupiedFieldBattleResult extractOutput(UnoccupiedFieldBattleModel model){
                return new UnoccupiedFieldBattleResult();
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
