package macior.strategygame.service.chainOfResponsibility.nodes.upgradeBuilding;


import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.models.UpgradeBuildingModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.utilities.errors.GameErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingRequestValidator extends Node {


    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        UpgradeRequest request = (UpgradeRequest) upgradeModel.REQUEST;
        if (request == null){
            setError(upgradeModel);
            return;
        }
        if (request.getLocation() == null){
            setError(upgradeModel);
            return;
        }
        if (request.getPlace() > 5 || request.getPlace() < 1){
            setError(upgradeModel);
        }
    }

    private void setError(UpgradeBuildingModel model){
        model.RESPONSE.setStatus(GameErrors.BAD_REQUEST);
    }
}
