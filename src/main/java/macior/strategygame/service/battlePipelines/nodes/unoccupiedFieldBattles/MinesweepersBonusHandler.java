package macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.battlePipelines.models.UnoccupiedFieldBattleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinesweepersBonusHandler extends ChainNode<UnoccupiedFieldBattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(UnoccupiedFieldBattleModel model, ChainExecutor executor) {
        PlayersUpgradesSet upgrades = model.ATTACK.ATTACKER.getUpgradesSet();
        if (upgrades.upgraded(Upgrades.MINESWEEPERS)){
            model.ATTACKERS_DEFENCE = (int)(model.ATTACKERS_BASE_DEFENCE * (1+configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getMinesweepers().HITPOINT_BONUS));
        }
    }
}
