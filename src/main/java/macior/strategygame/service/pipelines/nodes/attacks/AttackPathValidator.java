package macior.strategygame.service.pipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.AreaUnitOwnershipValidator;
import macior.strategygame.service.pipelines.sharedUtilities.PathValidation;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackPathValidator extends ChainNode<ArmyTransferModel> {

    @Autowired
    private AreaUnitOwnershipValidator ownershipValidator;

    @Autowired
    private PathValidation pathValidator;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest request = (AttackRequest)model.REQUEST;
        Location[] path = request.getPath();

        if (!pathValidator.isLongEnough(path)){
            setError(model);
            executor.stop();
            return;
        }

        if (!pathValidator.areEndsCorrect(request, path)){
            setError(model);
            executor.stop();
            return;
        }

        for (int i = 0; i < path.length-2; i++){
            if (!ownershipValidator.isLocationOwnedBy(model.PLAYER, path[i])){
                setError(model);
                executor.stop();
                return;
            }
        }

        if (!pathValidator.areAllNeighbours(path)){
            setError(model);
            executor.stop();
        }
    }
    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.WRONG_PATH);
    }


}
