package macior.strategygame.service.playerCommandPipelines.nodes.buildNewBuilding;


import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.service.playerCommandPipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.playerCommandPipelines.models.ChainModel;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingRequestValidator extends ChainNode<ChainModel> {

    @Override
    public void execute(ChainModel model, ChainExecutor executor) {
        BuildingRequest request = (BuildingRequest) ((BuildNewBuildingModel)model).REQUEST;
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
        if (request.getPlace() > 5 || request.getPlace() < 2){
            setError(model);
            executor.stop();
            return;
        }
        if (request.getBuilding() < 1 || request.getBuilding() > 7){
            setError(model);
            executor.stop();
        }
    }

    private void setError(ChainModel model){
        model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
    }


}
