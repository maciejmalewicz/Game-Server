package macior.strategygame.service.chainOfResponsibility.nodes.armyTraining;

import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTrainingModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.chainOfResponsibility.sharedUtilities.ArmyQuantityGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingTimeGetter extends Node {

    @Autowired
    private GameConfiguration configuration;

    @Autowired
    private ArmyQuantityGetter quantityGetter;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        ArmyTrainingRequest request = (ArmyTrainingRequest)trainingModel.REQUEST;

        double basicTime = trainingModel.MECH_CONFIG.DURATION;
        int quantity = quantityGetter.getProductionCostQuantity(trainingModel.FACTORY_CONFIG, request);
        int totalTime = (int)(Math.ceil(basicTime*quantity));

        TimeManager timeManager = trainingModel.PLAYER.getGame().getTimeManager();
        trainingModel.FINISHING_TIME = timeManager.getPostponedEventTime(totalTime);
    }


}
