package macior.strategygame.service.pipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class TimeValidator extends ChainNode<PlayerChangesModel> {

    private static int MAX_TIME = 3600;

    @Override
    public void execute(PlayerChangesModel model, ChainExecutor executor) {
        if (MAX_TIME < model.FINISHING_TIME || model.FINISHING_TIME < 0){
            executor.stop();
            model.RESPONSE.setStatus(GameErrors.NOT_ENOUGH_TIME);
        }
    }
}
