package macior.strategygame.service.pipelines.nodes.armyTraining;

import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.mechFactory.MechFactory;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTrainingEvent;
import macior.strategygame.models.game.playersControls.ArmyTrainingRequest;
import macior.strategygame.service.pipelines.models.ArmyTrainingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.ArmyQuantityGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmyTrainingEventStarter extends Node {

    @Autowired
    private ArmyQuantityGetter armyQuantityGetter;

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTrainingModel trainingModel = (ArmyTrainingModel)model;
        EventFactory eventFactory = trainingModel.PLAYER.getGame().getEventFactory();
        ArmyTrainingRequest request = (ArmyTrainingRequest)trainingModel.REQUEST;
        int quantity = armyQuantityGetter.getProductionQuantity(trainingModel.FACTORY_CONFIG,
                (MechFactory) trainingModel.FACTORY, request.productionType);

        ArmyTrainingEvent event = eventFactory.generateArmyTrainingEvent(
                trainingModel.FINISHING_TIME,
                trainingModel.AREA_UNIT,
                request.unitType,
                quantity);

        EventHandler eventHandler = trainingModel.PLAYER.getGame().getEventHandler();
        AreaEventsQueue eventsQueue = trainingModel.AREA_UNIT.getEventsQueue();

        eventHandler.addEvent(event);
        eventsQueue.pushEvent(event);
    }
}
