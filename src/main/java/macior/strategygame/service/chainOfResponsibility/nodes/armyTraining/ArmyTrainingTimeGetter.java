package macior.strategygame.service.chainOfResponsibility.nodes.armyTraining;

import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.configuration.ArmyUpgradesConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
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
        TimeResponse timeResponse = (TimeResponse)trainingModel.RESPONSE;

        double basicTime = trainingModel.MECH_CONFIG.DURATION;
        int quantity = quantityGetter.getProductionCostQuantity(trainingModel.FACTORY_CONFIG, request);
        int totalTime = (int)(Math.ceil(basicTime*quantity));
        //for droids, apply some duration perks/penalties
        totalTime = applyDurationBonuses(trainingModel.PLAYER.getUpgradesSet(), request, totalTime);

        int finishingTime;
        if (trainingModel.EVENT == null){
            TimeManager timeManager = trainingModel.PLAYER.getGame().getTimeManager();
            finishingTime = timeManager.getPostponedEventTime(totalTime);

        } else {
            int lastEventFinishingTime = trainingModel.EVENT.getFinishingTime();
            finishingTime = lastEventFinishingTime + totalTime;
        }

        trainingModel.FINISHING_TIME = finishingTime;
        timeResponse.setFinishingTime(finishingTime);
    }

    private int applyDurationBonuses(PlayersUpgradesSet upgrades, ArmyTrainingRequest request, int totalTime){
        if (request.unitType != 1){
            return totalTime;
        }
        ArmyUpgradesConfig config = configuration.getUpgradesConfig().getArmyUpgradesConfig();
        double discount = 0.0;

        if (upgrades.upgraded(Upgrades.POWER_IN_SIMPLICITY)){
            discount += config.getPowerInSimplicity().PRODUCTION_TIME_REDUCTION;
        }

        if (upgrades.upgraded(Upgrades.ADVANCED_DROIDS)){
            discount -= config.getAdvancedDroids().PRODUCTION_TIME_INCREASE;
        }

        totalTime = (int)(totalTime*(1-discount));
        return totalTime;
    }


}
