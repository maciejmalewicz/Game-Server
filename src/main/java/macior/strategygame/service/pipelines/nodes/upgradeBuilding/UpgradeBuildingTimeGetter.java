package macior.strategygame.service.pipelines.nodes.upgradeBuilding;

import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.pipelines.models.ChainModel;
import macior.strategygame.service.pipelines.models.UpgradeBuildingModel;
import macior.strategygame.service.pipelines.nodes.Node;
import macior.strategygame.service.pipelines.sharedUtilities.TimeGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpgradeBuildingTimeGetter extends Node {

    @Autowired
    private GameConfiguration configuration;

    @Autowired
    private TimeGetter timeGetter;


    @Override
    public void applyChanges(ChainModel model) {
        UpgradeBuildingModel upgradeModel = (UpgradeBuildingModel)model;
        BuildingConfig buildingConfig = upgradeModel.BUILDING_CONFIG;
        //to model
        upgradeModel.FINISHING_TIME = timeGetter.getTime(upgradeModel, buildingConfig);
        //to response
        TimeResponse timeResponse = (TimeResponse)upgradeModel.RESPONSE;
        timeResponse.setFinishingTime(upgradeModel.FINISHING_TIME);
    }

//    private int getTime(BuildingUpgradingConcernedModel model, BuildingConfig config){
//        BuildingConcernedEvent event = model.LATEST_EVENT;
//        TimeManager timeManager = model.PLAYER.getGame().getTimeManager();
//        Building toUpgrade = model.BUILDING_UPGRADED;
//        PlayersUpgradesSet upgrades = model.PLAYER.getUpgradesSet();
//
//        //here is the main logic
//        //when will building or upgrading finish
//        int currentFinishingTime = getCurrentFinishingTime(event, timeManager);
//        //how much time does this upgrade last
//        int basicTime = getBasicTime(config, model.NEXT_LEVEL);
//        //reduce this time maybe, depending on upgrades
//        basicTime = applyDurationBonuses(basicTime, toUpgrade, upgrades);
//
//        //adding time when currently lasting upgrades are finished to the new upgrades duration
//        return basicTime + currentFinishingTime;
//    }
//
//    //get basic time of an upgrade
//    private int getBasicTime(BuildingConfig config, int level){
//        return config.getTime(level).toSeconds();
//    }
//
//    //applyDurationBonuses
//    private int applyDurationBonuses(int duration, Building building, PlayersUpgradesSet upgrades){
//        double discount = 0.0;
//        if (isDroneTower(building) && upgrades.upgraded(Upgrades.SCRAP_DRONES)){
//            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
//                    .getScrapDrones().BUILDING_TIME_REDUCTION;
//        }
//        if (upgrades.upgraded(Upgrades.BUILDING_ENGINEERS)){
//            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
//                    .getBuildingEngineers().BUILDING_TIME_REDUCTION;
//        }
//        return (int)(duration*(1-discount));
//    }
//
//    private boolean isDroneTower(Building building){
//        if (building.getClass() == Observatory.class){
//            return true;
//        }
//        if (building.getClass() == UnderConstructionBuilding.class){
//            UnderConstructionBuilding b = (UnderConstructionBuilding)building;
//            return b.getBuildingUnderConstruction().getClass() == Observatory.class;
//        }
//        return false;
//    }
//
//    //get time when all currently lasting upgrades finish
//    private int getCurrentFinishingTime(BuildingConcernedEvent event, TimeManager timeManager){
//        if (event == null){
//            return timeManager.getSecondsFromStart();
//        }
//        return event.getFinishingTime();
//    }
}
