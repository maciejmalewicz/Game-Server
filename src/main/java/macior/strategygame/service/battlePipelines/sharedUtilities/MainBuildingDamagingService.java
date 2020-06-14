package macior.strategygame.service.battlePipelines.sharedUtilities;

import macior.strategygame.game.BoardManagement.AreaEventsQueue;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PostponedEvents.EventHandler;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingUpgradeEvent;
import macior.strategygame.service.battlePipelines.models.BattleModel;
import org.springframework.stereotype.Component;

@Component
public class MainBuildingDamagingService {

    public void damageMainBuilding(BattleModel battleModel){ //if accuracy is not upgraded
        Building building = battleModel.ATTACK.TO.getBigBuilding();

        //leave it if there is nothing to be damaged
        if (building == null){
            return;
        }
        BuildingConcernedEvent event = getLatestEvent(battleModel);
        int level = getBigBuildingLevel(battleModel, event);
        //don't destroy building completely
        if (level == 1){
            return;
        }
        PlayersUpgradesSet upgrades = battleModel.ATTACK.ATTACKER.getUpgradesSet();
        if (!upgrades.upgraded(Upgrades.ACCURACY)){
            doDestroy(building, event, battleModel);
        }
    }

    private void doDestroy(Building building, BuildingConcernedEvent event, BattleModel model){
        if (event == null){
            building.LEVEL--;
        } else {
            AreaEventsQueue eventsQueue = model.ATTACK.TO.getEventsQueue();
            EventHandler eventHandler = model.ATTACK.ATTACKER.getGame().getEventHandler();
            eventsQueue.removeEvent(event);
            eventHandler.getUpcomingEvents().remove(event);
        }
    }

    private int getBigBuildingLevel(BattleModel model, BuildingConcernedEvent event){
        if (event == null){
            return model.ATTACK.TO.getBigBuilding().LEVEL;
        } else {
            if (event.getClass() == BuildingConstructionEvent.class){
                return 1;
            } else {
                return ((BuildingUpgradeEvent)event).getLevel();
            }
        }
    }

    private BuildingConcernedEvent getLatestEvent(BattleModel model){
        Building building = model.ATTACK.TO.getBigBuilding();
        if (building.getClass() == UnderConstructionBuilding.class){
            building = ((UnderConstructionBuilding)building).getBuildingUnderConstruction();
        }
        AreaEventsQueue queue = model.ATTACK.TO.getEventsQueue();
        BuildingConcernedEvent event = queue.getLastEventConcerningBuilding(building);
        return event;
    }
}
