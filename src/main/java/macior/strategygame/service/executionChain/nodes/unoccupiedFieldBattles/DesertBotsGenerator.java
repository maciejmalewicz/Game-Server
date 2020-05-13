package macior.strategygame.service.executionChain.nodes.unoccupiedFieldBattles;

import macior.strategygame.game.TimeManager;
import macior.strategygame.game.Utilities.RandomGenerator;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.executionChain.models.UnoccupiedFieldBattleModel;
import macior.strategygame.service.executionChain.nodes.ExecutionNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DesertBotsGenerator extends ExecutionNode<UnoccupiedFieldBattleModel> {

    @Autowired
    private GameConfiguration configuration;

    @Override
    public void execute(UnoccupiedFieldBattleModel model) {
        TimeManager timeManager = model.ATTACK.ATTACKER.getGame().getTimeManager();
        double currentSeconds = timeManager.getSecondsFromStart();
        double maxSeconds = configuration.getMainConfig().GAME_TIME;
        double timeProportion = currentSeconds/maxSeconds;

        int minCoefficient = configuration.getMainConfig().MIN_SPAWN_COEFFICIENT;
        int maxCoefficient = configuration.getMainConfig().MAX_SPAWN_COEFFICIENT;

        double spawningBias = configuration.getMainConfig().SPAWN_BIAS;
        double curvatureCoefficient = configuration.getMainConfig().SPAWN_CURVATURE_COEFFICIENT;

        double minAmount = minCoefficient*(timeProportion + spawningBias)
                *(Math.pow(curvatureCoefficient, 1 - timeProportion));
        double maxAmount = maxCoefficient*(timeProportion + spawningBias)
                *(Math.pow(curvatureCoefficient, 1 - timeProportion));

        int min = (int)Math.ceil(minAmount);
        int max = (int)Math.ceil(maxAmount);

        model.DESERT_BOTS = RandomGenerator.generateBetween(min, max);
    }
}
