package macior.strategygame.service.playerCommandPipelines.nodes;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.service.playerCommandPipelines.models.AreaUnitChangingModel;
import macior.strategygame.service.playerCommandPipelines.models.ChainModel;
import macior.strategygame.service.playerCommandPipelines.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaUnitValidator extends ChainNode<AreaUnitChangingModel> {

    @Autowired
    private AreaUnitOwnershipValidator validator;

    @Override
    public void execute(AreaUnitChangingModel model, ChainExecutor executor) {
        AreaConcernedRequest request = model.REQUEST;
        model.AREA_UNIT = model.PLAYER.getGame().getBoard()
                .getAreaUnit(request.getLocation());
        if (!validator.isAreaOwnedBy(model.PLAYER, model.AREA_UNIT)){
            executor.stop();
            setError(model);
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.AREA_UNIT_NOT_OWNED);
    }
}
