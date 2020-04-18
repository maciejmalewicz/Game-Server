package macior.strategygame.service.game.playersControlls;

import macior.strategygame.game.BoardManagement.AreaUnit;
import macior.strategygame.game.BoardManagement.Buildings.buildings.Building;
import macior.strategygame.game.BoardManagement.Buildings.buildings.UnderConstructionBuilding;
import macior.strategygame.game.BoardManagement.Buildings.buildings.smallBuildings.SmallBuilding;
import macior.strategygame.game.BoardManagement.Buildings.configurationObjects.BuildingConfig;
import macior.strategygame.game.PlayersManagement.Laboratory.PlayersUpgradesSet;
import macior.strategygame.game.PlayersManagement.Laboratory.Upgrades.Upgrades;
import macior.strategygame.game.PlayersManagement.Player;
import macior.strategygame.game.PostponedEvents.buildingConcernedEvents.BuildingConstructionEvent;
import macior.strategygame.game.PostponedEvents.EventFactory;
import macior.strategygame.game.Utilities.ResourceSet;
import macior.strategygame.models.game.configuration.GameConfiguration;
import macior.strategygame.models.game.playersControls.BuildingRequest;
import macior.strategygame.models.game.playersControls.TimeResponse;
import macior.strategygame.service.UserValidationService;
import macior.strategygame.service.utilities.mapper.PlayerGameMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildNewBuildingService {

    @Autowired
    private PlayerGameMapperService mapper;

    @Autowired
    private BuildingsPlacesMapperService buildingsMapper;

    @Autowired
    private UserValidationService validationService;

    @Autowired
    private GameConfiguration configuration;

    public TimeResponse buildNew(BuildingRequest request, String code){
        //int id  = mapper.getId(code);
        TimeResponse timeResponse = new TimeResponse();

        //unknown user
//        if (id == -1){
//            timeResponse.setStatus(-1);
//            timeResponse.setCode("UNKNOWN CODE");
//            return timeResponse;
//        }
//
//        String newCode = mapper.updateCode(code);
//        timeResponse.setCode(newCode);
        int id = validationService.validateAndGetId(code, timeResponse);
        if (id == -1){
            return timeResponse;
        }

        //if not playing any game
        Player player = mapper.getPlayerById(id);
        if (player == null){
            timeResponse.setStatus(-2);
            return timeResponse;
        }

        if (!isRequestOK(request)){
            timeResponse.setStatus(-100);
            return timeResponse;
        }

        if (!areUpgradesCompleted(request, player.getUpgradesSet())){
            timeResponse.setStatus(-7);
            return timeResponse;
        }

        if (!isPlayersFieldOK(player, request)){
            timeResponse.setStatus(-3);
            return timeResponse;
        }

        if (!isPlaceEmpty(player, request)){
            timeResponse.setStatus(-4);
            return timeResponse;
        }

        int timeWhenFinishes = getTimeWhenFinishes(player, request);
        if (timeWhenFinishes == -1){
            timeResponse.setStatus(-5);
            return timeResponse;
        }
        timeResponse.setFinishingTime(timeWhenFinishes);

        ResourceSet priceToPay = getPrice(player, request);
        if (priceToPay == null){
            timeResponse.setStatus(-6);
            return timeResponse;
        }

        //if everything goes fine:

        //pay for building
        payForBuilding(player, priceToPay);

        //affect the board with new toy-building
        UnderConstructionBuilding wrapper = setConstructionWrapper(request, player);

        //add to upcoming events, so it becomes a real building later
        addToEvents(wrapper, player, timeWhenFinishes);

        //if everything goes fine
        timeResponse.setStatus(0);
        return timeResponse;
    }

    private boolean isRequestOK(BuildingRequest request){
        if (request == null){
            System.out.println("null request");
            return false;
        }
        if (request.getLocation() == null){
            System.out.println("null location");
            return false;
        }
        if (request.getPlace() > 5 || request.getPlace() < 2){
            return false;
        }
        if (request.getBuilding() < 1 || request.getBuilding() > 7){
            return false;
        }
        return true;
    }


    private boolean isPlayersFieldOK(Player player, BuildingRequest request){
        AreaUnit unit = player.getGame().getBoard().getAreaUnit(request.getLocation());
        if (unit == null){
            return false;
        }
        return unit.getOwner() == player;
    }

    private boolean isPlaceEmpty(Player player, BuildingRequest request){
        AreaUnit unit = player.getGame().getBoard().getAreaUnit(request.getLocation());
        Building building = buildingsMapper.getBuilding(unit, request.getPlace());
        return building == null;
    }

    //returns null if player can't afford it, so it's more like get resources spent
    private ResourceSet getPrice(Player player, BuildingRequest buildingRequest){
        BuildingConfig config = buildingsMapper.getConfiguration(buildingRequest.getBuilding());
        ResourceSet price = config.LEVEL1_COST;
        ResourceSet resourcesSpent = price.canPurchase(player);
        return resourcesSpent;
    }

    private int getTimeWhenFinishes(Player player, BuildingRequest request){
        BuildingConfig config = buildingsMapper.getConfiguration(request.getBuilding());
        int seconds = config.LEVEL1_BUILDING_TIME.toSeconds();
        seconds = applyDurationDiscounts(seconds, request, player.getUpgradesSet());
        return player.getGame().getTimeManager().getPostponedEventTime(seconds);
    }

    private int applyDurationDiscounts(int time, BuildingRequest request, PlayersUpgradesSet upgrades){
        double discount = 0.0;
        if (request.getBuilding() == 4 && upgrades.upgraded(Upgrades.SCRAP_DRONES)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getScrapDrones().BUILDING_TIME_REDUCTION;
        }
        if (upgrades.upgraded(Upgrades.BUILDING_ENGINEERS)){
            discount += configuration.getUpgradesConfig().getControlUpgradesConfig()
                    .getBuildingEngineers().BUILDING_TIME_REDUCTION;
        }
        return (int)((1-discount)*time);
    }

    private void payForBuilding(Player player, ResourceSet toPay){
        player.getResources().substractResources(toPay);
    }

    //binds wrapper to place in area unit in game
    private UnderConstructionBuilding setConstructionWrapper(BuildingRequest request, Player player){
        SmallBuilding beingBuilt = buildingsMapper.getSmallBuilding(request.getBuilding());
        AreaUnit areaUnit = player.getGame().getBoard().getAreaUnit(request.getLocation());
        UnderConstructionBuilding wrapper =
                new UnderConstructionBuilding(beingBuilt, areaUnit, request.getPlace());
        //where and what is being added
        areaUnit.setBuilding(request.getPlace(), wrapper);
        return wrapper;
    }


    //we have 2 event lists: main and area unit
    private synchronized void addToEvents(UnderConstructionBuilding building, Player player, int finishTime){
        EventFactory factory = player.getGame().getEventFactory();
        BuildingConstructionEvent eventToAdd = factory.generateBuildingConstructionEvent(finishTime, building);
        building.getAreaUnit().getBuildingQueue().pushEvent(eventToAdd);
        player.getGame().getEventHandler().addEvent(eventToAdd);
    }

    private boolean areUpgradesCompleted(BuildingRequest request, PlayersUpgradesSet upgrades){
        if (request.getBuilding() == 6){
            return upgrades.upgraded(Upgrades.TANKS);
        }
        if (request.getBuilding() == 7){
            return upgrades.upgraded(Upgrades.CANNONS);
        }
        return true;
    }

}
