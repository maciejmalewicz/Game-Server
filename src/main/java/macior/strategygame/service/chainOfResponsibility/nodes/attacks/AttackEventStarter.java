package macior.strategygame.service.chainOfResponsibility.nodes.attacks;

import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.AttackEvent;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.chainOfResponsibility.models.ArmyTransferModel;
import macior.strategygame.service.chainOfResponsibility.models.ChainModel;
import macior.strategygame.service.chainOfResponsibility.nodes.Node;
import macior.strategygame.service.game.battles.BattleStartingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackEventStarter extends Node {

    @Autowired
    private BattleStartingService battleStarter;


    @Override
    public void applyChanges(ChainModel model) {
        ArmyTransferModel transferModel = (ArmyTransferModel)model;
        AttackRequest attackRequest = (AttackRequest)transferModel.REQUEST;
        EventFactory eventFactory = transferModel.PLAYER.getGame().getEventFactory();

        Attack attack = new Attack();
        attack.ARMY = attackRequest.getArmy();
        attack.ATTACKER = transferModel.PLAYER;
        attack.DEFENDER = transferModel.TARGET_AREA_UNIT.getOwner();
        attack.FROM = transferModel.AREA_UNIT;
        attack.TO = transferModel.TARGET_AREA_UNIT;
        attack.USING_COMMANDER = attackRequest.usingCommander;

        AttackEvent event = eventFactory.generateAttackEvent(transferModel.FINISHING_TIME,
                attack, battleStarter);

        EventHandler handler = transferModel.PLAYER.getGame().getEventHandler();
        AreaEventsQueue queue = transferModel.AREA_UNIT.getEventsQueue();

        handler.addEvent(event);
        queue.pushEvent(event);
    }
}
