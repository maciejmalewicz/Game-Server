package macior.strategygame.service.battlePipelines.nodes.unoccupiedFieldBattles;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.configuration.DesertBotsConfig;
import macior.strategygame.service.battlePipelines.models.UnoccupiedFieldBattleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesertBotsStrengthGetter extends ChainNode<UnoccupiedFieldBattleModel> {

    @Autowired
    private DesertBotsConfig configuration;

    @Override
    public void execute(UnoccupiedFieldBattleModel model, ChainExecutor executor) {
        model.DEFENDERS_ATTACK = model.DESERT_BOTS*configuration.ATTACK;
        model.DEFENDERS_BASE_DEFENCE = model.DESERT_BOTS*configuration.DEFENCE;

        model.DEFENDERS_DEFENCE = model.DEFENDERS_BASE_DEFENCE;
    }
}
