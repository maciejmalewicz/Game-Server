package macior.strategygame.service.pipelines.nodes.buildNewBuilding;

import executionChains.ChainNode;
import executionChains.chainExecutors.ChainExecutor;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.service.pipelines.models.BuildNewBuildingModel;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.nodes.Node;
import org.springframework.stereotype.Component;

@Component
public class BuildNewBuildingEventStarter extends ChainNode<BuildNewBuildingModel> {

    @Override
    public void execute(BuildNewBuildingModel model, ChainExecutor executor) {
        UnderConstructionBuilding building = model.UNDER_CONSTRUCTION_BUILDING;
        Player player = model.PLAYER;
        int finishTime = model.FINISHING_TIME;

        EventFactory factory = player.getGame().getEventFactory();
        BuildingConstructionEvent eventToAdd = factory.generateBuildingConstructionEvent(finishTime, building);
        building.getAreaUnit().getEventsQueue().pushEvent(eventToAdd);
        player.getGame().getEventHandler().addEvent(eventToAdd);
    }
}
