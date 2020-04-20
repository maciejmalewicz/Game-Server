package macior.strategygame.service.chainOfResponsibility.sharedUtilities;

import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Observatory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.TimeManager;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.service.chainOfResponsibility.models.BuildingUpgradingConcernedModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeGetter {

    @Autowired
    private GameConfiguration configuration;

    public int getTime(BuildingUpgradingConcernedModel model, BuildingConfig config){
        BuildingConcernedEvent event = model.LATEST_EVENT;
        TimeManager timeManager = model.PLAYER.getGame().getTimeManager();
        Building toUpgrade = model.BUILDING_UPGRADED;
        PlayersUpgradesSet upgrades = model.PLAYER.getUpgradesSet();

        //here is the main logic
        //when will building or upgrading finish
        int currentFinishingTime = getCurrentFinishingTime(event, timeManager);
        //how much time does this upgrade last
        int basicTime = getBasicTime(config, model.NEXT_LEVEL);
        //reduce this time maybe, depending on upgrades
        basicTime = applyDurationBonuses(basicTime, toUpgrade, upgrades);

        //adding time when currently lasting upgrades are finished to the new upgrades duration
        return basicTime + currentFinishingTime;
    }

    //get basic time of an upgrade
    private int getBasicTime(BuildingConfig config, int level){
        return config.getTime(level).toSeconds();
    }

    //applyDurationBonuses
    private int applyDurationBonuses(int duration, Building building, PlayersUpgradesSet upgrades){
        double discount = 0.0;
        if (building == null){
            return duration;
        }
        if (isDroneTower(building) && upgrades.upgraded(Upgrades.SCRAP_DRONES)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getScrapDrones().BUILDING_TIME_REDUCTION;
        }
        if (upgrades.upgraded(Upgrades.BUILDING_ENGINEERS)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getBuildingEngineers().BUILDING_TIME_REDUCTION;
        }
        return (int)(duration*(1-discount));
    }

    private boolean isDroneTower(Building building){
        if (building.getClass() == Observatory.class){
            return true;
        }
        if (building.getClass() == UnderConstructionBuilding.class){
            UnderConstructionBuilding b = (UnderConstructionBuilding)building;
            return b.getBuildingUnderConstruction().getClass() == Observatory.class;
        }
        return false;
    }

    //get time when all currently lasting upgrades finish
    private int getCurrentFinishingTime(BuildingConcernedEvent event, TimeManager timeManager){
        if (event == null){
            return timeManager.getSecondsFromStart();
        }
        return event.getFinishingTime();
    }
}
