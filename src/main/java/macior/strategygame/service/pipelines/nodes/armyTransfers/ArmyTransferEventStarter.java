package macior.strategygame.service.pipelines.nodes.armyTransfers;

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
public class ArmyTransferEventStarter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        ArmyTransferRequest request = (ArmyTransferRequest)transferModel.REQUEST;
        EventFactory eventFactory = transferModel.PLAYER.getGame().getEventFactory();

        ArmyTransferEvent event = eventFactory.generateArmyTransferEvent(
                transferModel.FINISHING_TIME,
                transferModel.AREA_UNIT,
                transferModel.TARGET_AREA_UNIT,
                transferModel.PLAYER,
                request.getArmy());

        AreaEventsQueue sendersEventQueue = transferModel.AREA_UNIT.getEventsQueue();
        EventHandler eventHandler = transferModel.PLAYER.getGame().getEventHandler();
        AreaEventsQueue receiversEventQueue = transferModel.TARGET_AREA_UNIT.getEventsQueue();

        sendersEventQueue.pushEvent(event);
        eventHandler.addEvent(event);
        receiversEventQueue.pushEvent(event);
    }
}
