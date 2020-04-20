package macior.strategygame.service.chainOfResponsibility.nodes.upgradeWalls;

import macior.strategygame.models.game.playersControls.AreaConcernedRequest;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeWallsModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.stereotype.Component;

@Component
public class UpgradeWallsRequestValidator extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeWallsModel wallsModel = (UpgradeWallsModel)model;
        AreaConcernedRequest request = wallsModel.REQUEST;
        if (request == null || request.getLocation() == null){
            model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
        }
    }
}
