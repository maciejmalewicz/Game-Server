package macior.strategygame.service.pipelines.nodes.upgradeBuilding;


import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingRequestValidator extends ChainNode<UpgradeBuildingModel> {


    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        UpgradeRequest request = (UpgradeRequest) model.REQUEST;
        if (request == null){
            setError(model);
            executor.stop();
            return;
        }
        if (request.getLocation() == null){
            setError(model);
            executor.stop();
            return;
        }
        if (request.getPlace() > 5 || request.getPlace() < 1){
            setError(model);
            executor.stop();
        }
    }

    private void setError(UpgradeBuildingModel model){
        model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
    }


}
