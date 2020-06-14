package macior.strategygame.service.pipelines.nodes.attacks;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BattlesManagement.Attack;
import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.PostponedEvents.armyConcernedEvents.AttackEvent;
import macior.strategygame.models.game.playersControls.AttackRequest;
import macior.strategygame.service.pipelines.models.ArmyTransferModel;
import macior.strategygame.service.game.battles.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttackEventStarter extends ChainNode<ArmyTransferModel> {

    @Autowired
    private BattleService battleStarter;

    @Override
    public void execute(ArmyTransferModel model, ChainExecutor executor) {
        AttackRequest attackRequest = (AttackRequest)model.REQUEST;
        EventFactory eventFactory = model.PLAYER.getGame().getEventFactory();

        Attack attack = new Attack();
        attack.ARMY = attackRequest.getArmy();
        attack.ATTACKER = model.PLAYER;
        attack.DEFENDER = model.TARGET_AREA_UNIT.getOwner();
        attack.FROM = model.AREA_UNIT;
        attack.TO = model.TARGET_AREA_UNIT;
        attack.USING_COMMANDER = attackRequest.usingCommander;

        AttackEvent event = eventFactory.generateAttackEvent(model.FINISHING_TIME,
                attack, battleStarter);

        EventHandler handler = model.PLAYER.getGame().getEventHandler();
        AreaEventsQueue queue = model.AREA_UNIT.getEventsQueue();

        handler.addEvent(event);
        queue.pushEvent(event);
    }
}
