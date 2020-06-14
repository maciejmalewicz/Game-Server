package macior.strategygame.service.playerCommandPipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.service.playerCommandPipelines.models.PlayerChangesModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class PaymentExecutor extends ChainNode<PlayerChangesModel> {

    //send an error if player can't afford it
    @Override
    public void execute(PlayerChangesModel model, ChainExecutor executor) {
        if (model.PRICE == null){
            model.RESPONSE.setStatus(GameErrors.NOT_ENOUGH_RESOURCES);
            executor.stop();
        } else {
            model.PLAYER.getResources().substractResources(model.PRICE);
        }
    }
}
