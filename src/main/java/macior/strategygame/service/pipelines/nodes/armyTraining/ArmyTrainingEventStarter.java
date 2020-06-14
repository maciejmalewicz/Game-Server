package macior.strategygame.service.pipelines.nodes.armyTraining;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
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
public class ArmyTrainingEventStarter extends ChainNode<ArmyTrainingModel> {

    @Autowired
    private ArmyQuantityGetter armyQuantityGetter;

    @Override
    public void execute(ArmyTrainingModel model, ChainExecutor executor) {
        EventFactory eventFactory = model.PLAYER.getGame().getEventFactory();
        ArmyTrainingRequest request = (ArmyTrainingRequest)model.REQUEST;
        int quantity = armyQuantityGetter.getProductionQuantity(model.FACTORY_CONFIG,
                (MechFactory) model.FACTORY, request.productionType);

        ArmyTrainingEvent event = eventFactory.generateArmyTrainingEvent(
                model.FINISHING_TIME,
                model.AREA_UNIT,
                request.unitType,
                quantity);

        EventHandler eventHandler = model.PLAYER.getGame().getEventHandler();
        AreaEventsQueue eventsQueue = model.AREA_UNIT.getEventsQueue();

        eventHandler.addEvent(event);
        eventsQueue.pushEvent(event);
    }
}
