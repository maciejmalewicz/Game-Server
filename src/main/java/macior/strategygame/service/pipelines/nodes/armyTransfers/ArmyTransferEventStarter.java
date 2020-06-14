package macior.strategygame.service.pipelines.nodes.armyTransfers;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.ArmyTransferEvent;
import macior.strategygame.models.game.playersControls.ArmyTransferRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class ArmyTransferEventStarter extends ChainNode<ArmyTransferModel> {

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        ArmyTransferRequest request = (ArmyTransferRequest)model.REQUEST;
        EventFactory eventFactory = model.PLAYER.getGame().getEventFactory();

        ArmyTransferEvent event = eventFactory.generateArmyTransferEvent(
                model.FINISHING_TIME,
                model.AREA_UNIT,
                model.TARGET_AREA_UNIT,
                model.PLAYER,
                request.getArmy());

        AreaEventsQueue sendersEventQueue = model.AREA_UNIT.getEventsQueue();
        EventHandler eventHandler = model.PLAYER.getGame().getEventHandler();
        AreaEventsQueue receiversEventQueue = model.TARGET_AREA_UNIT.getEventsQueue();

        sendersEventQueue.pushEvent(event);
        eventHandler.addEvent(event);
        receiversEventQueue.pushEvent(event);
    }
}
