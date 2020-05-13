package macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MinesweepersBonusHandler extends ExecutionNode<UnoccupiedFieldBattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(UnoccupiedFieldBattleModel model) {
        PlayersUpgradesSet upgrades = model.ATTACK.ATTACKER.getUpgradesSet();
        if (upgrades.upgraded(Upgrades.MINESWEEPERS)){
            model.ATTACKERS_DEFENCE = (int)(model.ATTACKERS_BASE_DEFENCE * (1+configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getMinesweepers().HITPOINT_BONUS));
        }
    }
}
