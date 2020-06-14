package macior.strategygame.service.pipelines.nodes.upgradeBuilding;

import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingEventStarter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        EventFactory eventFactory = upgradeModel.PLAYER.getGame().getEventFactory();
        UpgradeRequest request = (UpgradeRequest)upgradeModel.REQUEST;

        //creating
        BuildingUpgradeEvent event = eventFactory.generateBuildingUpgradeEvent(upgradeModel.FINISHING_TIME,
                upgradeModel.BUILDING_UPGRADED, upgradeModel.NEXT_LEVEL,
                upgradeModel.AREA_UNIT, request.getPlace());

        //adding to event handling queues
        upgradeModel.PLAYER.getGame().getEventHandler().addEvent(event);
        upgradeModel.AREA_UNIT.getEventsQueue().pushEvent(event);
    }
}
