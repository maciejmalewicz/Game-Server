package macior.strategygame.service.playerCommandPipelines.nodes.upgradeBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.playerCommandPipelines.models.UpgradeBuildingModel;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingEventStarter extends ChainNode<UpgradeBuildingModel> {

    @Override
    public void execute(UpgradeBuildingModel model, ChainExecutor executor) {
        EventFactory eventFactory = model.PLAYER.getGame().getEventFactory();
        UpgradeRequest request = (UpgradeRequest) model.REQUEST;

        //creating
        BuildingUpgradeEvent event = eventFactory.generateBuildingUpgradeEvent(model.FINISHING_TIME,
                model.BUILDING_UPGRADED, model.NEXT_LEVEL,
                model.AREA_UNIT, request.getPlace());

        //adding to event handling queues
        model.PLAYER.getGame().getEventHandler().addEvent(event);
        model.AREA_UNIT.getEventsQueue().pushEvent(event);
    }
}
