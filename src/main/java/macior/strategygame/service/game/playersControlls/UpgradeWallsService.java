package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.Walls;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.smallBuildings.WallsConfig;
import macior.strategygame.game.BoardManagement.Location;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConcernedEvent;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.WallsUpgradeEvent;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpgradeWallsService {

    @Autowired
    private UserValidationService validationService;

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private GameConfiguration configuration;

    public TimeResponse upgradeWalls(String code, Location location){
        TimeResponse response = new TimeResponse();
        int id = validationService.validateAndGetId(code, response);
        if (id == -1){
            response.setStatus(-1);
            return response;
        }

        Player player = mapper.getPlayerById(id);
        if (player == null){
            response.setStatus(-2);
            return response;
        }

        if (!isRequestValid(location)){
            response.setStatus(-3);
            return response;
        }

        AreaUnit unit = player.getGame().getBoard().getAreaUnit(location);
        if (!ownsAreaUnit(unit, player)){
            response.setStatus(-4);
            return response;
        }

        int level = getUpgradingLevel(unit);
        if (level == -1){
            response.setStatus(-5);
            return response;
        }

        ResourceSet price = getPriceToPay(level, player);
        if (price == null){
            response.setStatus(-6);
            return response;
        }

        int timeWhenFinishes = getTimeWhenFinishes(level, player);
        if (timeWhenFinishes == -1){
            response.setStatus(-7);
            return response;
        }

        response.setFinishingTime(timeWhenFinishes);

        payThePrice(price, player);

        Building building = getBuilding(level, unit);
        addToEvents(level, unit, timeWhenFinishes, building);
        //todo: test and fix bugs - there will be some
        return response;

    }

    private boolean isRequestValid(Location location){
        return location != null;
    }

    private boolean ownsAreaUnit(AreaUnit unit, Player player){
        if (unit == null){
            return false;
        }
        return unit.getOwner() == player;
    }

    //returns -1 if upgrading over max level
    private int getUpgradingLevel(AreaUnit unit){
        Walls walls = (Walls)unit.getWalls();
        if (walls == null){
            return 1;
        }
        int level = walls.LEVEL + 1;
        if (level > configuration.getSmallBuildingsConfig().getWallsConfig().MAX_LEVEL){
            return -1;
        }
        return level;
    }

    private ResourceSet getPriceToPay(int level, Player buyer){
        WallsConfig config = configuration.getSmallBuildingsConfig().getWallsConfig();
        ResourceSet price = config.getCost(level);

        if (buyer.getUpgradesSet().upgraded(Upgrades.ENGINEERING_PATTERNS)){
            price.multiplyResources(1 - configuration.getUpgradesConfig()
                    .getImprovementUpgradesConfig().getEngineeringPatterns().COST_REDUCTION);

        }
        return price.canPurchase(buyer);
    }


    private int getTimeWhenFinishes(int level, Player player){
        int time = getBuildingTime(level, player.getUpgradesSet());
        int finishingTime = player.getGame().getTimeManager().getPostponedEventTime(time);
        if (finishingTime > 3600){
            return -1;
        }
        return finishingTime;
    }

    private int getBuildingTime(int level, PlayersUpgradesSet upgrades){
        WallsConfig config = configuration.getSmallBuildingsConfig().getWallsConfig();
        int baseTime = config.getTime(level).toSeconds();
        if (upgrades.upgraded(Upgrades.BUILDING_ENGINEERS)){
            baseTime = (int)((1-configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getBuildingEngineers().BUILDING_TIME_REDUCTION)*baseTime);
        }
        return baseTime;
    }

    private void payThePrice(ResourceSet price, Player buyer){
        buyer.getResources().substractResources(price);
    }

    //sets up under construction building too
    private Building getBuilding(int level, AreaUnit unit){
        if (level == 1){
            Walls walls = new Walls();
            UnderConstructionBuilding building = new UnderConstructionBuilding(walls, unit, 6);
            unit.setWalls(building);
            return building;
        } else {
            return unit.getWalls();
        }
    }

    private void addToEvents(int level, AreaUnit unit, int time, Building building){
        EventFactory factory = unit.getOwner().getGame().getEventFactory();
        WallsUpgradeEvent event = factory.generateWallsUpgradeEvent(time, building, level, unit);
        unit.getBuildingQueue().pushEvent(event);
        unit.getOwner().getGame().getEventHandler().addEvent(event);
    }


}
