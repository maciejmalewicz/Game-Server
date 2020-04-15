package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.BuildingQueue;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Observatory;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.resourceFactories.SmallFactoryConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.ControlUpgrades.ScrapDrones;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.BuildingUpgradeEvent;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.TimeManager;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.models.game.playersControls.UpgradeRequest;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;

@Service
public class UpgradeBuildingService {

    @Autowired
    private UserValidationService userValidation;

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private BuildingsPlacesMapperService buildingsMapper;

    @Autowired
    private GameConfiguration configuration;

    public TimeResponse upgradeBuilding(String code, UpgradeRequest request){
        TimeResponse response = new TimeResponse();
        int id = userValidation.validateAndGetId(code, response);
        if (id == -1){
            response.setStatus(-1);
            return response;
        }

        //check if is playing
        Player player = mapper.getPlayerById(id);
        if (player == null){
            response.setStatus(-2);
            return response;
        }

        //nothing in the request is missing
        if (!isRequestOk(request)){
            response.setStatus(-100);
            return response;
        }

        //player owns an existing area unit
        AreaUnit areaUnit = getAreaUnit(player, request);
        if (!isPlayersFieldOK(player, areaUnit)){
            response.setStatus(-3);
            return response;
        }

        //tries to upgrade actually an existing building
        Building toUpgrade = getBuilding(areaUnit, request.getPlace());
        if (toUpgrade == null){
            response.setStatus(-4);
            return response;
        }

        BuildingConfig buildingConfig = getBuildingConfiguration(toUpgrade);
        BuildingConcernedEvent event = getLatestEvent(toUpgrade, areaUnit);

        //doesn't try to upgrade over the max level
        int currentUpgradingLevel = getCurrentUpgradingLevel(toUpgrade, event);
        if (!canBeUpgradedMore(toUpgrade, player, currentUpgradingLevel)){
            response.setStatus(-5);
            return response;
        }
        int levelToUpgrade = currentUpgradingLevel+1;

        //when is building going to be build
        int finishingTime = getTime(toUpgrade, levelToUpgrade, event,
                player.getGame().getTimeManager(), player.getUpgradesSet());

        if (finishingTime == -1){
            response.setStatus(-6);
            return response;
        }

        //if player can afford it
        ResourceSet priceToPay = getCost(player, getPrice(buildingConfig, levelToUpgrade, player.getUpgradesSet()));
        if (priceToPay == null){
            response.setStatus(-7);
            return response;
        }

        //everything went fine so

        //we pay
        payThePrice(priceToPay, player);

        //we start up event, so it will be handled later
        EventFactory factory = player.getGame().getEventFactory();
        BuildingUpgradeEvent upgradeEvent = factory.generateBuildingUpgradeEvent(finishingTime, toUpgrade,
                levelToUpgrade, areaUnit, request.getPlace());
        setEvent(upgradeEvent, player, areaUnit);

        response.setStatus(0);
        response.setFinishingTime(finishingTime);
        return response;
    }

    private boolean isRequestOk(UpgradeRequest request){
        if (request.getLocation() == null){
            return false;
        }
        if (request.getPlace() > 5 || request.getPlace() < 1){
            return false;
        }
        return true;
    }

    private AreaUnit getAreaUnit(Player player, UpgradeRequest request){
        return player.getGame().getBoard().getAreaUnit(request.getLocation());
    }

    private boolean isPlayersFieldOK(Player player, AreaUnit unit){
        if (unit == null){
            return false;
        }
        return unit.getOwner() == player;
    }

    private Building getBuilding(AreaUnit unit, int place){
        return buildingsMapper.getBuilding(unit, place);
    }

    private BuildingConfig getBuildingConfiguration(Building building){
        return buildingsMapper.getConfiguration(building);
    }

    private boolean canBeUpgradedMore(Building building, Player player, int currentUpgradingLevel){
        BuildingConfig config = buildingsMapper.getConfiguration(building);
        int currentMaxLevel = getCurrentMaxLevel(config, player.getUpgradesSet());
        return currentUpgradingLevel < currentMaxLevel;
    }

    //fetch buildings latest upgrade or building process
    private BuildingConcernedEvent getLatestEvent(Building building, AreaUnit unit){
        return unit.getBuildingQueue().getLastEventConcerningBuilding(building);
    }

    //what is current level of the building including lasting upgrades
    private int getCurrentUpgradingLevel(Building building, BuildingConcernedEvent event){
        //unpack wrapper if building hasn't been built yet
        if (building.getClass() == UnderConstructionBuilding.class){
            UnderConstructionBuilding ucb = (UnderConstructionBuilding)building;
            building = ucb.getBuildingUnderConstruction();
        }
        //if nothing is happening with the building, just return it's level
        if (event == null){
            return building.LEVEL;
        }
        if (event.getClass() == BuildingConstructionEvent.class){
            return 1;
        }
        if (event.getClass() == BuildingUpgradeEvent.class){
            BuildingUpgradeEvent upgradeEvent = (BuildingUpgradeEvent)event;
            return upgradeEvent.getLevel();
        }
        return -1;
    }

    //returns maximum upgrade level of the building, depending on players upgrades
    private int getCurrentMaxLevel(BuildingConfig config, PlayersUpgradesSet upgrades){
        int level = config.MAX_LEVEL;
        if (!(config instanceof SmallFactoryConfig)){
            return level;
        }
        //handle small factories
        if (!upgrades.upgraded(Upgrades.SPACE_MANAGEMENT)){ //space management not upgraded
            return --level;
        }
        return level;
    }


    //time

    private int getTime(Building toUpgrade, int level, BuildingConcernedEvent event, TimeManager timeManager, PlayersUpgradesSet upgrades){
        int currentFinishingTime = getCurrentFinishingTime(event, timeManager);
        int basicTime = getBasicTime(toUpgrade, level);
        basicTime = applyDurationBonuses(basicTime, toUpgrade, upgrades);
        return getActualFinishingTime(currentFinishingTime, basicTime, timeManager);
    }

    //get basic time of an upgrade
    private int getBasicTime(Building toUpgrade, int level){
        BuildingConfig config = buildingsMapper.getConfiguration(toUpgrade);
        return config.getTime(level).toSeconds();
    }

    //applyDurationBonuses
    private int applyDurationBonuses(int duration, Building building, PlayersUpgradesSet upgrades){
        double discount = 0.0;
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

    private int getActualFinishingTime(int currentFinishingTime, int basicTime, TimeManager manager){
        int time = currentFinishingTime + basicTime;
        if (!manager.canStillBuild(time)){
            return -1;
        }
        return time;
    }

    //PAYMENT

    //returns amount of resources that player has to pay, or null if he can't afford it
    private ResourceSet getCost(Player player, ResourceSet price){
        return price.canPurchase(player);
    }

    //calculates price that has to be paid, including engineering patterns upgrade
    private ResourceSet getPrice(BuildingConfig config, int level, PlayersUpgradesSet upgrades) {
        ResourceSet basePrice = config.getCost(level);
        if (upgrades.upgraded(Upgrades.ENGINEERING_PATTERNS)) {
            double discount = configuration.getUpgradesConfig().getImprovementUpgradesConfig()
                    .getEngineeringPatterns().COST_REDUCTION;
            discount = 1 - discount;
            basePrice.multiplyResources(discount);
        }
        return basePrice;
    }

    private void payThePrice(ResourceSet toPay, Player player){
        player.getResources().substractResources(toPay);
    }

    private void setEvent(BuildingUpgradeEvent event, Player player, AreaUnit unit){
        player.getGame().getEventHandler().addEvent(event);
        unit.getBuildingQueue().pushEvent(event);
    }


}
