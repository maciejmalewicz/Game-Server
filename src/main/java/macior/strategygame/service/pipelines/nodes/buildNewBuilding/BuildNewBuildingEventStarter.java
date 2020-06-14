package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingEventStarter extends Node {

    @Override
    public void applyChanges(ChainModel model) {
        BuildNewBuildingModel buildingModel = (BuildNewBuildingModel)model;
        UnderConstructionBuilding building = buildingModel.UNDER_CONSTRUCTION_BUILDING;
        Player player = buildingModel.PLAYER;
        int finishTime = buildingModel.FINISHING_TIME;

        EventFactory factory = player.getGame().getEventFactory();
        BuildingConstructionEvent eventToAdd = factory.generateBuildingConstructionEvent(finishTime, building);
        building.getAreaUnit().getEventsQueue().pushEvent(eventToAdd);
        player.getGame().getEventHandler().addEvent(eventToAdd);
    }
}
